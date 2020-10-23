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
@Table(name="alumnus")
public class Alumnus {
    @Id
    @Column(name="alumnus_id",type= MySqlTypeConstant.VARCHAR, isKey=true)
    private String alumnusId;

    @Column(name="alumnus_name",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String alumnusName;

    @Column(name="introduction",type=MySqlTypeConstant.VARCHAR)
    private String introduction;

}
