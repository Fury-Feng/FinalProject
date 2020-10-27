package com.bham.project.dao.mapper;

import com.bham.project.entity.Applicant;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.lang.Nullable;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/15
 */
public interface ApplicantMapper extends Mapper<Applicant> {
    @Select("SELECT * FROM applicant WHERE aid=#{aid}")
    @Results(id="ApplicantMap", value={@Result(property = "aid", column = "aid", id = true),
        @Result(property = "ugSchool", column = "ug_school"),
        @Result(property = "ugMajor", column = "ug_major"),
        @Result(property = "ugGpa", column = "ug_gpa"),
        @Result(property = "personalIntro", column = "personal_intro"),
        @Result(property = "ielts", column = "aid",
        one=@One(select = "com.bham.project.dao.mapper.IeltsMapper.selectByAid",
                fetchType = FetchType.EAGER)),
        @Result(property = "toefl", column = "aid",
        one=@One(select = "com.bham.project.dao.mapper.ToeflMapper.selectByAid"))
    })
    public Applicant selectByAid(@Param("aid") String aid);

    @Insert("INSERT into applicant (aid) VALUES (#{applicant.aid})")
    public int addApplicant(@Param("applicant") Applicant applicant);

    @Update("UPDATE applicant SET ug_school=#{ug_school}, ug_gpa=#{ug_gpa}, " +
            "ug_major=#{ug_major}, personal_intro=#{personal_intro} " +
            "WHERE aid=#{aid}")
    public int editApplicant(@Nullable @Param("ug_school") String ugSchool,
                             @Nullable @Param("ug_gpa") float ugGpa,
                             @Nullable @Param("ug_major") String ugMajor,
                             @Nullable @Param("personal_intro") String intro,
                             @Param("aid") String aid);
}
