package cn.mybatis.mp.datasource.routing.test.dao;

import cn.mybatis.mp.core.mvc.Dao;
import cn.mybatis.mp.datasource.routing.test.DO.Druid;

public interface DruidDao extends Dao<Druid, Integer>, SuperDao<Druid> {

    void test1();

    void test2();

    void test3();

    void test4();

    void testAddDatasource();

    void testSpel(int i);


}
