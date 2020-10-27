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
@Table(name="applicant")
public class Applicant {

    @Id
    @Column(name="aid", type= MySqlTypeConstant.VARCHAR, isKey=true)
    private String aid;

    @Column(name="ug_school", type=MySqlTypeConstant.VARCHAR)
    private String ugSchool;

    @Column(name="ug_major", type=MySqlTypeConstant.VARCHAR)
    private String ugMajor;

    @Column(name="ug_gpa", type=MySqlTypeConstant.FLOAT)
    private Float ugGpa;

    @Column(name="personal_intro", type=MySqlTypeConstant.VARCHAR)
    private String personalIntro;

    private Ielts ielts;

    private Toefl toefl;

}
