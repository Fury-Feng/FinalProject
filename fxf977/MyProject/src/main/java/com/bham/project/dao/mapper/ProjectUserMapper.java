package com.bham.project.dao.mapper;

import com.bham.project.entity.ProjectUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/16
 */
public interface ProjectUserMapper extends Mapper<ProjectUser> {
    @Select("SELECT * FROM project_user WHERE username=#{username}")
    @Results(id="ProjectUserMap", value={@Result(property="userId", column="user_id", id=true),
            @Result(property="username", column="username"),
            @Result(property="password", column="password"),
            @Result(property="email", column="email"),
            @Result(property="country", column="country")})
    public ProjectUser selectByUserName(@Param("username") String userName);

    @Insert("INSERT INTO project_user (username, password, email, country) VALUES" +
            "(#{puser.username}, #{puser.password}, #{puser.email}, #{puser.country})")
    public int addUser(@Param("puser") ProjectUser newUser);
}
