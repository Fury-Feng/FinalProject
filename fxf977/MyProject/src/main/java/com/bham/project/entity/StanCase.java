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
@Table(name="stan_case")
public class StanCase {

    @Id
    @Column(name="case_id",type=MySqlTypeConstant.INT, isKey=true, isNull=false, isAutoIncrement=true)
    private Integer caseId;

    @Column(name="school_id",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String schoolId;

    @Column(name="degree",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String degree;

    @Column(name="major_id",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String majorId;

    // 0 = offer, 1 = reject
    @Column(name="result",type=MySqlTypeConstant.INT, isNull=false)
    private Integer result;

    // 0 = autumn, 1 = spring
    @Column(name="term",type=MySqlTypeConstant.INT, isNull=false)
    private Integer term;

    @Column(name="result_date",type=MySqlTypeConstant.VARCHAR)
    private String resultDate;

    @Column(name="aid",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String aid;

    private School school;
    private Major major;
    private Applicant applicant;
}
