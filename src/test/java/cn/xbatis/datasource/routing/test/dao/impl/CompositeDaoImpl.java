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

import cn.xbatis.datasource.routing.DS;
import cn.xbatis.datasource.routing.test.RoutingDataSourceType;
import cn.xbatis.datasource.routing.test.dao.CompositeDao;
import cn.xbatis.datasource.routing.test.dao.DruidDao;
import cn.xbatis.datasource.routing.test.dao.HikariDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@DS(RoutingDataSourceType.DRUID)
public class CompositeDaoImpl implements CompositeDao {

    @Resource
    private HikariDao hikariDao;

    @Resource
    private DruidDao druidDao;

    @Transactional
    @Override

    public void test4() {
        druidDao.test3();
        hikariDao.test3();


    }

    @Override
    @Transactional
    public void testSuperMethod() {
        druidDao.getById2(1);
        hikariDao.getById2(1);
    }
}
