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
@Table(name="major")
public class Major {
    @Id
    @Column(name="major_id",type= MySqlTypeConstant.VARCHAR, isKey=true)
    private String majorId;

    @Column(name="school_id",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String schoolId;

    @Column(name="major_rank",type=MySqlTypeConstant.INT)
    private Integer majorRank;

    @Column(name="major_name",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String majorName;

    @Column(name="course_set",type=MySqlTypeConstant.VARCHAR)
    private String courseSet;

    @Column(name="language_con",type=MySqlTypeConstant.FLOAT)
    private Float languageCon;

    @Column(name="study_hours",type=MySqlTypeConstant.FLOAT)
    private Float studyHours;
}
