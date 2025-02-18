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

package cn.xbatis.datasource.routing.test;


import cn.xbatis.datasource.routing.test.dao.DefaultTestDao;
import cn.xbatis.datasource.routing.SpringRoutingDataSource;
import cn.xbatis.datasource.routing.test.dao.CompositeDao;
import cn.xbatis.datasource.routing.test.dao.DruidDao;
import cn.xbatis.datasource.routing.test.dao.HikariDao;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertFalse;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@ComponentScan("cn.xbatis")
@MapperScan("cn.xbatis.datasource.routing.test.mapper")
@SpringBootTest(classes = SpringRoutingDataSourceTest.class)
@ExtendWith(SpringExtension.class)
public class SpringRoutingDataSourceTest {

    @Resource
    private DruidDao druidDao;

    @Resource
    private HikariDao hikariDao;

    @Resource
    private CompositeDao compositeDao;

    @Resource
    private SpringRoutingDataSource springRoutingDataSource;

    @Resource
    private DefaultTestDao defaultTestDao;

    @Test
    @Order(1)
    public void test() {
        hikariDao.test1();
        druidDao.test1();

        hikariDao.test2();
        druidDao.test2();


        druidDao.test3();

        hikariDao.test3();


        hikariDao.test4();
        druidDao.test4();
//
        compositeDao.test4();

    }

    @Test
    @Order(Integer.MAX_VALUE)
    public void testAddNewDatabase() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:h2:mem:druidDB2234;INIT=RUNSCRIPT FROM 'classpath:db/druidDB.sql'");
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setDriverClassName("org.h2.Driver");
        ds.setAutoCommit(false);

        springRoutingDataSource.addNewDatasource(RoutingDataSourceType.NEW_ADD + "-1", ds);


        druidDao.testAddDatasource();

        springRoutingDataSource.removeDatasource(RoutingDataSourceType.NEW_ADD + "-1");

        assertFalse(springRoutingDataSource.getResolvedDataSources().containsKey(RoutingDataSourceType.NEW_ADD));
        assertFalse(springRoutingDataSource.getResolvedDataSources().containsKey(RoutingDataSourceType.NEW_ADD + "-1"));


        springRoutingDataSource.removeDatasource(RoutingDataSourceType.HIKARI);
        assertFalse(springRoutingDataSource.getResolvedDataSources().containsKey(RoutingDataSourceType.HIKARI));
        springRoutingDataSource.removeDatasource(RoutingDataSourceType.DRUID);
        assertFalse(springRoutingDataSource.getResolvedDataSources().containsKey(RoutingDataSourceType.DRUID));
        springRoutingDataSource.removeDatasource("NOT EXISTS");
    }

    @Test
    @Order(2)
    public void testSepl() {
        druidDao.testSpel(1);
        druidDao.testSpel(2);
        druidDao.testSpel(3);
    }

    @Test
    @Order(3)
    public void defaultDatabaseTest() {
        defaultTestDao.defaultDatabaseTest();
    }

    @Test
    public void testSuperMethod() {
        compositeDao.testSuperMethod();
    }
}
