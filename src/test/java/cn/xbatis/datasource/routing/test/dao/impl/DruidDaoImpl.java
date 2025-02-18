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

package cn.xbatis.datasource.routing.test.dao.impl;

import cn.xbatis.core.mvc.impl.DaoImpl;
import cn.xbatis.datasource.routing.test.DO.Druid;
import cn.xbatis.datasource.routing.DS;
import cn.xbatis.datasource.routing.test.RoutingDataSourceType;
import cn.xbatis.datasource.routing.test.dao.DruidDao;
import cn.xbatis.datasource.routing.test.mapper.DruidMapper;
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
