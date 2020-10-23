package com.bham.project.dao.mapper;

import com.bham.project.entity.Major;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/16
 */
public interface MajorMapper extends Mapper<Major> {
    @Select("SELECT * FROM major WHERE school_id=#{school_id}")
    @Results(id="MajorMap", value= {@Result(property="majorId", column="major_id", id=true),
            @Result(property="schoolId", column="school_id"),
            @Result(property="majorRank", column="major_rank"),
            @Result(property="majorName", column="major_name"),
            @Result(property="courseSet", column="course_set"),
            @Result(property="languageCon", column="language_con"),
            @Result(property="studyHours", column="study_hours")})
    public List<Major> selectBySchoolId(@Param("school_id") String schoolId);

    @Select("SELECT major_id FROM major WHERE major_name=#{major_name}")
    @Result(property = "majorId", column = "major_id", id = true)
    public List<String> selectMajorIdByMajorName(@Param("major_name") String majorName);

    @Select("SELECT * FROM major WHERE school_id=#{school_id} AND major_name=#{major_name}")
    @ResultMap(value = {"MajorMap"})
    public Major selectBySchoolIdAndMajorName(@Param("school_id") String schoolId,
                                              @Param("major_name") String majorName);

    @Select("SELECT * FROM major WHERE school_id=#{school_id} limit 1")
    @ResultMap(value = {"MajorMap"})
    public Major selectOneMajorBySchoolId(@Param("school_id") String schoolId);
}
