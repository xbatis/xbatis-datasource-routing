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

package cn.xbatis.datasource.routing;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = Config.PREFIX + ".aop", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RoutingDataSourceAopProperties.class)
@AutoConfigureAfter(value = RoutingDataSourceAutoConfiguration.class)
public class RoutingDataSourceAopAutoConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    RoutingDataSourceSpringInterceptor routingDataSourceSpringInterceptor() {
        return new RoutingDataSourceSpringInterceptor();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    RoutingDataSourceAbstractPointcutAdvisor routingDataSourceAbstractPointcutAdvisor(RoutingDataSourceAopProperties routingDataSourceAopProperties, RoutingDataSourceSpringInterceptor routingDataSourceSpringInterceptor) {
        RoutingDataSourceAbstractPointcutAdvisor pointcutAdvisor = new RoutingDataSourceAbstractPointcutAdvisor(routingDataSourceSpringInterceptor);
        pointcutAdvisor.setOrder(routingDataSourceAopProperties.getOrder());
        return pointcutAdvisor;
    }
}
