package cn.mybatis.mp.datasource.routing.test.dao.impl;

import cn.mybatis.mp.core.mvc.impl.DaoImpl;
import cn.mybatis.mp.datasource.routing.test.DO.Druid;
import cn.mybatis.mp.datasource.routing.DS;
import cn.mybatis.mp.datasource.routing.test.RoutingDataSourceType;
import cn.mybatis.mp.datasource.routing.test.dao.DruidDao;
import cn.mybatis.mp.datasource.routing.test.mapper.DruidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@DS(RoutingDataSourceType.DRUID)
public class DruidDaoImpl extends DaoImpl<Druid, Integer> implements DruidDao {


    public DruidDaoImpl(@Autowired DruidMapper druidMapper) {
        super(druidMapper);
    }

    @Override
    public DruidMapper getMapper() {
        return (DruidMapper) super.getMapper();
    }

    @Override
    public void test1() {
        this.getById(1);
    }

    @Override
    @DS(RoutingDataSourceType.DRUID)
    public void test2() {
        this.getById(1);
    }

    @Override
    @Transactional
    public void test3() {
        test1();
        test2();
    }

    private void aa() {
        this.getById(2);
    }

    @Override
    public void test4() {
        this.aa();
    }

    @Override
    @DS(RoutingDataSourceType.NEW_ADD)
    public void testAddDatasource() {
        this.getById(1);
    }

    @Override
    @DS("{'slave-'+(#id % 2)}")
    public void testSpel(int id) {
        this.getById(1);
    }


}
