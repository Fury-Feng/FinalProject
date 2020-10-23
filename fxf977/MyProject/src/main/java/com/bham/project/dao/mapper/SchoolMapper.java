package com.bham.project.dao.mapper;

import com.bham.project.entity.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/16
 */
public interface SchoolMapper extends Mapper<School> {
    @Select("SELECT * FROM school WHERE school_id=#{school_id}")
    @Results(id = "SchoolMap", value = {@Result(property = "schoolId", column = "school_id", id = true),
        @Result(property = "schoolName", column = "school_name"),
        @Result(property = "email", column = "email"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "url", column = "url"),
        @Result(property = "rejectRate", column = "reject_rate"),
        @Result(property = "environment", column = "environment"),
        @Result(property = "location", column = "location"),
        @Result(property = "schoolTitle", column = "school_title"),
        @Result(property = "tuitionFee", column = "tuition_fee"),
        @Result(property = "openDate", column = "open_date"),
        @Result(property = "days", column = "days"),
        @Result(property = "requiredIelts", column = "required_ielts"),
        @Result(property = "address", column = "address"),
        @Result(property = "schoolRank", column = "school_id",
        one=@One(select = "com.bham.project.dao.mapper.SchoolRankMapper.selectByPrimaryKey",
                fetchType = FetchType.EAGER)),
        @Result(property = "majorList", column = "school_id",
        many=@Many(select = "com.bham.project.dao.mapper.MajorMapper.selectBySchoolId",
                fetchType = FetchType.EAGER))})
    public School selectBySchoolId(@Param("school_id") String schoolId);

    @Select("select school_id from school where school_name=#{school_name}")
    @Result(property = "schoolId", column = "school_id")
    public String selectSchoolIdByName(@Param("school_name") String schoolName);
}
