package cn.mybatis.mp.datasource.routing.test.dao;

import cn.mybatis.mp.core.mvc.Dao;
import cn.mybatis.mp.datasource.routing.test.DO.Hikari;

public interface DefaultTestDao extends Dao<Hikari, Integer> {

    void defaultDatabaseTest();
}
