package cn.mybatis.mp.datasource.routing.test.dao.impl;

import cn.mybatis.mp.core.mvc.impl.DaoImpl;
import cn.mybatis.mp.datasource.routing.test.DO.Hikari;
import cn.mybatis.mp.datasource.routing.test.dao.DefaultTestDao;
import cn.mybatis.mp.datasource.routing.test.mapper.HikariMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTestDaoImpl extends DaoImpl<Hikari, Integer> implements DefaultTestDao {

    public DefaultTestDaoImpl(@Autowired HikariMapper hikariMapper) {
        super(hikariMapper);
    }

    @Override
    public void defaultDatabaseTest() {
        this.getById(1);
    }
}
