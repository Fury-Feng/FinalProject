package com.bham.project.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/12
 */
@Data
@Table(name="toefl")
public class Toefl {
    @Id
    @Column(name="aid", type=MySqlTypeConstant.VARCHAR, isKey=true)
    private String aid;

    @Column(name="read", type=MySqlTypeConstant.FLOAT, isNull=false)
    private Float read;

    @Column(name="listen", type=MySqlTypeConstant.FLOAT, isNull=false)
    private Float listen;

    @Column(name="speak", type=MySqlTypeConstant.FLOAT, isNull=false)
    private Float speak;

    @Column(name="write", type=MySqlTypeConstant.FLOAT, isNull=false)
    private Float write;

    @Column(name="overall", type=MySqlTypeConstant.FLOAT, isNull=false)
    private Float overall;
}
