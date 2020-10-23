package com.bham.project.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Id;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/12
 */
@Data
@Table(name="project_user")
public class ProjectUser {

    @Id
    @Column(name="user_id", type=MySqlTypeConstant.INT, isKey=true, isAutoIncrement=true)
    private Integer userId;

    @Column(name="username", type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String username;

    @Column(name="password", type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String password;

    @Column(name="email", type=MySqlTypeConstant.VARCHAR, isNull=false)
    private String email;

    @Column(name="country", type=MySqlTypeConstant.VARCHAR)
    private String country;

    public ProjectUser(String userName, String password, String email, @Nullable String country) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.country = country;
    }

}
