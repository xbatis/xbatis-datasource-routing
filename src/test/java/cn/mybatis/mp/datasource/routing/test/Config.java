package cn.mybatis.mp.datasource.routing.test;

import cn.mybatis.mp.datasource.routing.DefaultRoutingDataSourceSwitchContext;
import cn.mybatis.mp.datasource.routing.JdbcConfigDecryptor;
import cn.mybatis.mp.datasource.routing.RoutingDataSourceSwitchContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class Config {

    /**
     * 创建JdbcConfig解密 Bean
     *
     * @return
     */
    @Bean
    JdbcConfigDecryptor jdbcConfigDecryptor() {
        return new TestJdbcConfigDecryptor();
    }

    @Bean
    RoutingDataSourceSwitchContext routingDataSourceSwitchContext() {
        return new DefaultRoutingDataSourceSwitchContext();
    }
}
