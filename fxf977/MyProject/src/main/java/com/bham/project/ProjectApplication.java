package com.bham.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bham.project.dao.mapper", "com.bham.project.controller", "com.bham.project.service.impl", "com.bham.project.service", "com.gitee.sunchenbin.mybatis.actable.manager"})
@MapperScan({"com.bham.project.service","com.bham.project.dao.mapper", "com.bham.project.service.impl", "com.gitee.sunchenbin.mybatis.actable.dao.**"})
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
