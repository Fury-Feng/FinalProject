package com.bham.project.dao.mapper;

import com.bham.project.entity.SchoolsAlumnus;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/17
 */
public interface SchoolsAlumnusMapper extends Mapper<SchoolsAlumnus> {
    @Select("SELECT * FROM schools_alumnus WHERE s_a_id=#{s_a_id}")
    @Results(id="SchoolsAlumnusMap", value={@Result(property="sAId", column="s_a_id", id=true),
            @Result(property="schoolId", column="school_id"),
            @Result(property="alumnusId", column="alumnus_id"),
            @Result(property="school", column="school_id",
                    one=@One(select="com.bham.project.dao.mapper.SchoolMapper.selectBySchoolId",
                            fetchType= FetchType.EAGER)),
            @Result(property="alumnus", column="alumnus_id",
                    one=@One(select="com.bham.project.dao.mapper.AlumnusMapper.selectByPrimaryKey",
                            fetchType=FetchType.EAGER))})
    public SchoolsAlumnus selectBySAId(@Param("s_a_id") Integer sAId);

    @Select("SELECT * FROM schools_alumnus WHERE school_id=#{school_id}")
    @ResultMap(value = {"SchoolsAlumnusMap"})
    public List<SchoolsAlumnus> selectBySchoolId(@Param("school_id") String schoolId);
}
