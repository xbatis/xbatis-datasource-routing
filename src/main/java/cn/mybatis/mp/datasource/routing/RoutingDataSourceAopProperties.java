/*
 * Copyright © 2018 organization baomidou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.mybatis.mp.datasource.routing;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;


@ConfigurationProperties(prefix = Config.PREFIX + ".aop")
public class RoutingDataSourceAopProperties {

    /**
     * 是否开启aop
     */
    private Boolean enabled = true;

    /**
     * aop order
     */
    private int order = Ordered.HIGHEST_PRECEDENCE;

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}