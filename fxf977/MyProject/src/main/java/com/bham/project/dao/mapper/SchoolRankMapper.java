package com.bham.project.dao.mapper;

import com.bham.project.entity.SchoolRank;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/17
 */
public interface SchoolRankMapper extends Mapper<SchoolRank> {
    @Select("SELECT school_id FROM school_rank ORDER BY qs_rank")
    @Result(property="schoolId", column="school_id")
    public List<String> getSchoolIdByQS();

    @Select("SELECT school_id FROM school_rank ORDER BY times_rank")
    @Result(property="schoolId", column="school_id")
    public List<String> getSchoolIdByTimes();
}
