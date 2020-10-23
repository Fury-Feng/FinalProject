package com.bham.project.dao.mapper;

import com.bham.project.entity.UserApplicant;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/17
 */
public interface UserApplicantMapper extends Mapper<UserApplicant> {
    @Select("select * from user_applicant where oid=#{oid}")
    @Results(id = "UserApplicantMap", value = {@Result(property="oid", column="oid", id=true),
            @Result(property="aid", column="aid"),
            @Result(property="userId", column="user_id"),
            @Result(property="applicant", column="aid",
                    one=@One(select="com.bham.project.dao.mapper.ApplicantMapper.selectByAid",
                            fetchType= FetchType.EAGER)),
            @Result(property="projectUser", column="user_id",
                    one=@One(select="com.bham.project.dao.mapper.ProjectUserMapper.selectByPrimaryKey",
                            fetchType=FetchType.EAGER))})
    public UserApplicant selectByOid(@Param("oid") Integer oid);

    @Insert("INSERT INTO user_applicant (aid, user_id) VALUES (#{userApplicant.aid}, #{userApplicant.userId})")
    public int addUserApplicant(@Param("userApplicant") UserApplicant userApplicant);

    @Select("select * from user_applicant where user_id=#{user_id}")
    @ResultMap(value = {"UserApplicantMap"})
    public UserApplicant selectByUserId(@Param("user_id") Integer userId);
}
