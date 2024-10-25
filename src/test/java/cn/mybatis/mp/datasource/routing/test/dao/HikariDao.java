package cn.mybatis.mp.datasource.routing.test.dao;

import cn.mybatis.mp.core.mvc.Dao;
import cn.mybatis.mp.datasource.routing.test.DO.Hikari;

public interface HikariDao extends Dao<Hikari, Integer>, SuperDao<Hikari> {
    void test1();

    void test2();

    void test3();

    void test4();

    void aa3();
}
