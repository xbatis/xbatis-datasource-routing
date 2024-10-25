package cn.mybatis.mp.datasource.routing;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = Config.PREFIX + ".aop", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RoutingDataSourceAopProperties.class)
@AutoConfigureAfter(value = RoutingDataSourceAutoConfiguration.class)
public class RoutingDataSourceAopAutoConfiguration {

    @Bean
    RoutingDataSourceSpringInterceptor routingDataSourceSpringInterceptor() {
        return new RoutingDataSourceSpringInterceptor();
    }

    @Bean
    RoutingDataSourceAbstractPointcutAdvisor routingDataSourceAbstractPointcutAdvisor(RoutingDataSourceAopProperties routingDataSourceAopProperties, RoutingDataSourceSpringInterceptor routingDataSourceSpringInterceptor) {
        RoutingDataSourceAbstractPointcutAdvisor pointcutAdvisor = new RoutingDataSourceAbstractPointcutAdvisor(routingDataSourceSpringInterceptor);
        pointcutAdvisor.setOrder(routingDataSourceAopProperties.getOrder());
        return pointcutAdvisor;
    }
}
