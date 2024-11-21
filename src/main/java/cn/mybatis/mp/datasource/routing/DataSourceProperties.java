/*
 *  Copyright (c) 2024-2024, Ai东 (abc-127@live.cn).
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
import cn.mybatis.mp.datasource.routing.dataSourceConfig.DruidConfig;
import cn.mybatis.mp.datasource.routing.dataSourceConfig.HikariConfig;

import java.util.Properties;

public class DataSourceProperties extends org.springframework.boot.autoconfigure.jdbc.DataSourceProperties {

    /**
     * 配置的类型
     */
    private ConfigType configType;

    /**
     * hikari 数据连接池配置
     */
    private HikariConfig hikari;

    /**
     * druid 数据连接池配置
     */
    private DruidConfig druid;

    /**
     * other 数据连接池配置
     */
    private Properties other;

    public ConfigType getConfigType() {
        return configType;
    }

    public HikariConfig getHikari() {
        return hikari;
    }

    public void setHikari(HikariConfig hikari) {
        this.hikari = hikari;
        this.configType = ConfigType.HIKARI;
    }

    public DruidConfig getDruid() {
        return druid;
    }

    public void setDruid(DruidConfig druid) {
        this.druid = druid;
        this.configType = ConfigType.DRUID;
    }

    public Properties getOther() {
        return other;
    }

    public void setOther(Properties other) {
        this.other = other;
        this.configType = ConfigType.OTHER;
    }
}
