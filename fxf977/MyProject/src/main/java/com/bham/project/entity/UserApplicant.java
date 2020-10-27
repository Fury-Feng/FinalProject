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
@Table(name="user_applicant")
public class UserApplicant {

    @Id
    @Column(name="oid", type=MySqlTypeConstant.INT, isKey=true, isNull=false, isAutoIncrement=true)
    private Integer oid;

    @Column(name="aid", type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String aid;

    @Column(name="user_id", type=MySqlTypeConstant.INT, isNull=false)
    private Integer userId;

    private ProjectUser projectUser;
    private Applicant applicant;
}
