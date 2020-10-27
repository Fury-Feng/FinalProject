package com.bham.project.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/12
 */
@Data
@Table(name="school")
public class School {

    @Id
    @Column(name="school_id", type= MySqlTypeConstant.VARCHAR, isKey=true)
    private String schoolId;

    @Column(name="school_name",type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String schoolName;

    @Column(name="email",type=MySqlTypeConstant.VARCHAR)
    private String email;

    @Column(name="phone",type=MySqlTypeConstant.VARCHAR)
    private String phone;

    @Column(name="url",type=MySqlTypeConstant.VARCHAR)
    private String url;

    @Column(name="reject_rate",type=MySqlTypeConstant.FLOAT)
    private Float rejectRate;

    @Column(name="environment",type=MySqlTypeConstant.VARCHAR)
    private String environment;

    @Column(name="location",type=MySqlTypeConstant.VARCHAR)
    private String location;

    @Column(name="school_title",type=MySqlTypeConstant.TEXT)
    private String schoolTitle;

    @Column(name="tuition_fee",type=MySqlTypeConstant.FLOAT)
    private Float tuitionFee;

    @Column(name="open_date",type=MySqlTypeConstant.VARCHAR)
    private String openDate;

    @Column(name="days",type=MySqlTypeConstant.VARCHAR)
    private String days;

    @Column(name="required_ielts",type=MySqlTypeConstant.FLOAT)
    private Float requiredIelts;

    @Column(name="address",type=MySqlTypeConstant.VARCHAR)
    private String address;

    private SchoolRank schoolRank;

    private List<Major> majorList;
}
