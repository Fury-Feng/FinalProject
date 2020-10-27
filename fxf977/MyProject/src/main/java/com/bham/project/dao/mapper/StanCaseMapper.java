package com.bham.project.dao.mapper;

import com.bham.project.entity.StanCase;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/17
 */
public interface StanCaseMapper extends Mapper<StanCase> {
    @Select("SELECT * FROM stan_case WHERE case_id=#{case_id}")
    @Results(id="StanCaseMap", value={@Result(property="caseId", column="case_id", id=true),
            @Result(property="schoolId", column="school_id"),
            @Result(property="degree", column="degree"),
            @Result(property="majorId", column="major_id"),
            @Result(property="result", column="result"),
            @Result(property="term", column="term"),
            @Result(property="resultDate", column="result_date"),
            @Result(property="aid", column="aid"),
            @Result(property="school", column="school_id",
                    one=@One(select="com.bham.project.dao.mapper.SchoolMapper.selectByPrimaryKey",
                            fetchType= FetchType.EAGER)),
            @Result(property="applicant", column="aid",
                    one=@One(select="com.bham.project.dao.mapper.ApplicantMapper.selectByAid",
                            fetchType=FetchType.EAGER)),
            @Result(property = "major", column = "major_id",
                    one=@One(select="com.bham.project.dao.mapper.MajorMapper.selectByPrimaryKey"))})
    public StanCase selectByCaseId(@Param("case_id") Integer caseId);

    @Select("SELECT * FROM stan_case WHERE school_id=#{school_id}")
    @ResultMap(value = {"StanCaseMap"})
    public List<StanCase> selectBySchoolId(@Param("school_id") String schoolId);


    @Select("<script>"
            + "SELECT * FROM stan_case WHERE major_id in "
            + "<foreach item='major' collection='majorList' open='(' separator=',' close=')'>"
            + "#{major}"
            + "</foreach>"
            + "</script>")
    @ResultMap(value={"StanCaseMap"})
    public List<StanCase> selectByMajorsId(@Param("majorList") List<String> majorIdList);

    @Select("SELECT * FROM stan_case WHERE major_id=#{major_id}")
    @ResultMap(value = {"StanCaseMap"})
    public List<StanCase> selectByMajorId(@Param("major_id") String majorId);
}
