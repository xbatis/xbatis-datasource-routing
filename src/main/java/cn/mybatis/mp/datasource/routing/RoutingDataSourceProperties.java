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

import cn.mybatis.mp.datasource.routing.dataSourceConfig.SeataMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = Config.PREFIX)
public class RoutingDataSourceProperties {

    /**
     * 主数据源
     */
    private String primary;


    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 是否开启 p6spy
     */
    private Boolean p6spy = false;

    /**
     * 是否开启 seata
     */
    private Boolean seata = false;

    /**
     * seata 模式
     */
    private SeataMode seataMode;

    /**
     * 严格模式
     * true 时，未匹配到响应的 数据源时 报错
     */
    private Boolean strictMode = true;

    /**
     * 启用 jdbc配置解密
     */
    private Boolean jdbcConfigDecrypt = false;

    /**
     * 数据源 多个配置项
     */
    private Map<String, DataSourceProperties> routing;

    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * mybatis-mp 动态数据源开关
     *
     * @param enabled 是否开启
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getP6spy() {
        return p6spy;
    }

    public void setP6spy(Boolean p6spy) {
        this.p6spy = p6spy;
    }

    public String getPrimary() {
        return primary;
    }

    /**
     * 主数据库
     *
     * @param primary 主数据的key
     */
    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public Map<String, DataSourceProperties> getRouting() {
        return routing;
    }

    public void setRouting(Map<String, DataSourceProperties> routing) {
        this.routing = routing;
    }

    public Boolean getStrictMode() {
        return strictMode;
    }

    public void setStrictMode(Boolean strictMode) {
        this.strictMode = strictMode;
    }

    public Boolean getJdbcConfigDecrypt() {
        return jdbcConfigDecrypt;
    }

    public void setJdbcConfigDecrypt(Boolean jdbcConfigDecrypt) {
        this.jdbcConfigDecrypt = jdbcConfigDecrypt;
    }

    public Boolean getSeata() {
        return seata;
    }

    public void setSeata(Boolean seata) {
        this.seata = seata;
    }

    public SeataMode getSeataMode() {
        return seataMode;
    }

    public void setSeataMode(SeataMode seataMode) {
        this.seataMode = seataMode;
    }
}
