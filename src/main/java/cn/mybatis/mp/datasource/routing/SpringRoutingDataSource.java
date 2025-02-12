/*
 *  Copyright (c) 2024-2025, Ai东 (abc-127@live.cn).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License").
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

package cn.mybatis.mp.datasource.routing;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 基于spring的动态数据源
 */
public class SpringRoutingDataSource extends AbstractRoutingDataSource {

    private final Field resolvedDataSourcesField;

    private final Field targetDataSourcesField;

    public SpringRoutingDataSource() {
        try {
            resolvedDataSourcesField = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
            resolvedDataSourcesField.setAccessible(true);

            targetDataSourcesField = AbstractRoutingDataSource.class.getDeclaredField("targetDataSources");
            targetDataSourcesField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String determineCurrentLookupKey() {
        return DataSourceHolder.getCurrent();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = super.determineTargetDataSource();
        if (dataSource instanceof GroupDataSource) {
            return ((GroupDataSource) dataSource).loadBalance();
        }
        return dataSource;
    }

    public String getUrl() throws NoSuchMethodException {
        DataSource dataSource = this.determineTargetDataSource();
        String[] methodNames = new String[]{"getUrl", "getJdbcUrl"};

        Throwable throwable = null;
        for (String methodName : methodNames) {
            try {
                Method method = dataSource.getClass().getMethod(methodName);
                return (String) method.invoke(dataSource);
            } catch (Exception e) {
                if (Objects.isNull(throwable)) {
                    throwable = e;
                }
            }
        }
        throw new NoSuchMethodException();
    }

    private String getGroupName(String key) {
        if (!key.contains(Config.GROUP_SPLIT)) {
            return null;
        }
        int index = key.lastIndexOf(Config.GROUP_SPLIT);

        String no = key.substring(index + 1);
        try {
            Integer.parseInt(no);
        } catch (NumberFormatException e) {
            return null;
        }
        return key.substring(0, index);
    }

    /**
     * 添加新的数据源
     *
     * @param key
     * @param dataSource
     */
    public void addNewDatasource(String key, DataSource dataSource) {
        Map<Object, DataSource> dataSourceMap = this.getSelfResolvedDataSources();
        if (dataSourceMap.containsKey(key)) {
            throw new DataSourceExistsException();
        }
        String groupName = getGroupName(key);
        if (Objects.nonNull(groupName)) {
            GroupDataSource groupDataSource = (GroupDataSource) dataSourceMap.get(key);
            if (Objects.isNull(groupDataSource)) {
                List<DataSource> list = new ArrayList<>();
                list.add(dataSource);
                groupDataSource = new GroupDataSource(list);
                dataSourceMap.put(groupName, groupDataSource);
            } else {
                groupDataSource.getDelegateList().add(groupDataSource);
            }
        }
        dataSourceMap.put(key, dataSource);
    }


    private Map<Object, DataSource> getSelfResolvedDataSources() {
        try {
            return (Map<Object, DataSource>) resolvedDataSourcesField.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Object, DataSource> getSelfTargetDataSources() {
        try {
            return (Map<Object, DataSource>) this.targetDataSourcesField.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除数据源
     * 可移除分组数据源
     *
     * @param key
     * @return 返回被移除的数据库
     */
    public DataSource removeDatasource(String key) {
        Map<Object, DataSource> resolvedDataSources = this.getSelfResolvedDataSources();
        Map<Object, DataSource> targetDataSources = this.getSelfTargetDataSources();
        DataSource dataSource = removeTargetByKey(key, resolvedDataSources, targetDataSources);
        if (Objects.isNull(dataSource)) {
            return null;
        }
        if (dataSource instanceof GroupDataSource) {
            //移除分组数据源
            GroupDataSource groupDataSource = (GroupDataSource) dataSource;
            Iterator<Map.Entry<Object, DataSource>> iterable = resolvedDataSources.entrySet().iterator();
            while (iterable.hasNext()) {
                Map.Entry<Object, DataSource> entry = iterable.next();
                if (groupDataSource.getDelegateList().contains(entry.getValue())) {
                    removeTargetByKey(entry.getKey().toString(), targetDataSources);
                    iterable.remove();
                }
            }
        } else {
            String groupName = getGroupName(key);
            if (Objects.nonNull(groupName)) {
                GroupDataSource groupDataSource = (GroupDataSource) resolvedDataSources.get(groupName);
                if (Objects.nonNull(groupDataSource)) {
                    List<DataSource> list = groupDataSource.getDelegateList();
                    list.remove(dataSource);
                    if (list.isEmpty()) {
                        //全部移除了 移除分组
                        removeTargetByKey(groupName, resolvedDataSources, targetDataSources);
                    }
                }
            }
        }
        return dataSource;
    }

    @SafeVarargs
    private final DataSource removeTargetByKey(String key, Map<Object, DataSource>... dataSourceMaps) {
        DataSource dataSource = null;
        for (Map<Object, DataSource> dataSourceMap : dataSourceMaps) {
            if (Objects.isNull(dataSource)) {
                dataSource = dataSourceMap.remove(key);
            } else {
                dataSourceMap.remove(key);
            }
        }

        return dataSource;
    }
}
