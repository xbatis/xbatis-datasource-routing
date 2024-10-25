package cn.mybatis.mp.datasource.routing.test.DO;

import cn.mybatis.mp.db.annotations.Table;
import cn.mybatis.mp.db.annotations.TableId;

@Table
public class Druid {

    @TableId
    private Integer id;

    private String druidName;

}
