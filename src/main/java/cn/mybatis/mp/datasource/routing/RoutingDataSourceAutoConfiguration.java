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


import cn.mybatis.mp.datasource.routing.dataSourceConfig.ConfigType;
import cn.mybatis.mp.datasource.routing.dataSourceConfig.SeataMode;
import com.p6spy.engine.spy.P6DataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.*;


@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RoutingDataSourceProperties.class)
@AutoConfigureBefore(value = DataSourceAutoConfiguration.class)
@ConditionalOnProperty(prefix = Config.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RoutingDataSourceAutoConfiguration implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(RoutingDataSourceAutoConfiguration.class);

    private final Map<Object, Object> routingDataSources = new HashMap<>();
    private ApplicationContext applicationContext;

    @Primary
    @Bean(name = "dataSource")
    DataSource dataSource(RoutingDataSourceProperties routingDataSourceProperties) {
        Object primaryDataSource;
        if (StringUtils.hasText(routingDataSourceProperties.getPrimary())) {
            primaryDataSource = routingDataSources.get(routingDataSourceProperties.getPrimary());
        } else {
            Optional<Map.Entry<Object, Object>> firstOptional = routingDataSources.entrySet().stream().findFirst();
            primaryDataSource = firstOptional.map(Map.Entry::getValue).orElse(null);
        }
        Objects.requireNonNull(primaryDataSource, "找不到主数据源");
        SpringRoutingDataSource routingDataSource = new SpringRoutingDataSource();
        routingDataSource.setTargetDataSources(routingDataSources);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);
        routingDataSource.setLenientFallback(!routingDataSourceProperties.getStrictMode());
        logger.info("多数据源 初始化完成！！！！！！！！！！！！");
        return routingDataSource;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Binder binder = Binder.get(applicationContext.getEnvironment());
        RoutingDataSourceProperties routingDataSourceProperties = binder.bind(Config.PREFIX, Bindable.of(RoutingDataSourceProperties.class)).get();
        Map<String, List<DataSource>> groupDataSourceMap = new HashMap<>();
        final JdbcConfigDecryptor jdbcConfigDecryptor;
        if (routingDataSourceProperties.getJdbcConfigDecrypt()) {
            jdbcConfigDecryptor = applicationContext.getBean(JdbcConfigDecryptor.class);
        } else {
            jdbcConfigDecryptor = null;
        }

        routingDataSourceProperties.getRouting().entrySet().forEach(entry -> {

            ConfigType configType = entry.getValue().getConfigType();
            if (entry.getValue().getName() == null) {
                entry.getValue().setName(entry.getKey());
            }

            if (routingDataSourceProperties.getJdbcConfigDecrypt()) {
                entry.getValue().setUrl(jdbcConfigDecryptor.jdbcUrlDecrypt(entry.getValue().getUrl()));
                entry.getValue().setUsername(jdbcConfigDecryptor.usernameDecrypt(entry.getValue().getUsername()));
                entry.getValue().setPassword(jdbcConfigDecryptor.passwordDecrypt(entry.getValue().getPassword()));
            }

            DataSource dataSource = entry.getValue().initializeDataSourceBuilder().build();
            if (Objects.nonNull(configType)) {
                binder.bind(Config.ROUTING_PREFIX + "." + entry.getKey() + "." + configType.getKey(), Bindable.ofInstance(dataSource));
            }

            if (routingDataSourceProperties.getP6spy()) {
                dataSource = new P6DataSource(dataSource);
                logger.info("启用 p6spy");
            }

            if (routingDataSourceProperties.getSeata()) {
                if (Objects.isNull(routingDataSourceProperties.getSeataMode()) || SeataMode.XA == routingDataSourceProperties.getSeataMode()) {
                    routingDataSourceProperties.setSeataMode(SeataMode.XA);
                    dataSource = new DataSourceProxyXA(dataSource);
                } else if (SeataMode.AT == routingDataSourceProperties.getSeataMode()) {
                    dataSource = new DataSourceProxy(dataSource);
                }
                logger.info("开启 seata 事务：{}", routingDataSourceProperties.getSeataMode());
            }

            if (entry.getKey().contains(Config.GROUP_SPLIT)) {
                int index = entry.getKey().lastIndexOf(Config.GROUP_SPLIT);
                String groupName = entry.getKey().substring(0, index);
                String no = entry.getKey().substring(index + 1);
                try {
                    Integer.parseInt(no);
                    groupDataSourceMap.computeIfAbsent(groupName, key -> new ArrayList<>()).add(dataSource);
                } catch (NumberFormatException e) {
                    //ignore
                }
            }
            routingDataSources.put(entry.getKey(), dataSource);
            DataSource finalDataSource = dataSource;
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(DataSource.class, () -> finalDataSource).getBeanDefinition();
            beanDefinition.setPrimary(false);
            beanDefinitionRegistry.registerBeanDefinition(entry.getKey(), beanDefinition);
        });

        groupDataSourceMap.entrySet().forEach(groupEntry -> {
            if (routingDataSources.containsKey(groupEntry.getKey())) {
                throw new RuntimeException("重复的ds：" + groupEntry.getKey());
            }
            routingDataSources.put(groupEntry.getKey(), new GroupDataSource(groupEntry.getValue()));
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
