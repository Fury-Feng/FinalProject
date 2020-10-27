package com.bham.project.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/12
 */
@Data
@Table(name="school_rank")
public class SchoolRank {

    @Id
    @Column(name="school_id",type= MySqlTypeConstant.VARCHAR, isKey=true)
    private String schoolId;

    @Column(name="qs_rank",type=MySqlTypeConstant.INT, isNull=false)
    private Integer qsRank;

    @Column(name="times_rank",type=MySqlTypeConstant.INT, isNull=false)
    private Integer timesRank;

}
