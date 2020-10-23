package com.bham.project.service.impl;

import com.bham.project.dao.mapper.ApplicantMapper;
import com.bham.project.dao.mapper.ProjectUserMapper;
import com.bham.project.dao.mapper.UserApplicantMapper;
import com.bham.project.entity.Applicant;
import com.bham.project.entity.ProjectUser;
import com.bham.project.entity.UserApplicant;
import com.bham.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Autowired
    private UserApplicantMapper userApplicantMapper;

    @Autowired
    private ApplicantMapper applicantMapper;

    /**
     * The method is used to make user register, add the user information
     *
     * @param registerInfo The information to register
     * @return the user information whether the user is inserted
     */
    @Override
    public HashMap<String, Object> userRegister(HashMap<String, Object> registerInfo) {
        String username = registerInfo.get("username").toString();
        String password = registerInfo.get("password").toString();
        String country = registerInfo.get("country").toString();
        String email = registerInfo.get("email").toString();

        ProjectUser projectUser = projectUserMapper.selectByUserName(username);

        if (projectUser == null) {
            ProjectUser newUser = new ProjectUser(username, password, email, country);
            if (projectUserMapper.addUser(newUser) == 1) {
                ProjectUser resultUser = projectUserMapper.selectByUserName(username);
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", 200);
                resultMap.put("msg", "success");
                resultMap.put("user_id", resultUser.getUserId());
                return resultMap;
            }
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 500);
            resultMap.put("msg", "failed to add user to the database");
            return resultMap;
        } else {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 404);
            resultMap.put("msg", "The username is existed, please change the username");
            return resultMap;
        }
    }

    /**
     * The method is used to login
     *
     * @param loginInfo The information to login
     * @return The information whether the user is login
     */
    @Override
    public ProjectUser userLogin(HashMap<String, Object> loginInfo) {
        String username = loginInfo.get("username").toString();

        ProjectUser projectUser = projectUserMapper.selectByUserName(username);

        return projectUser;
    }

    @Override
    public HashMap<String, Object> getUserInfo(String username) {
        ProjectUser projectUser = projectUserMapper.selectByUserName(username);
        HashMap<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", projectUser.getUserId());
        data.put("username", projectUser.getUsername());
        data.put("email", projectUser.getEmail());
        data.put("country", projectUser.getCountry());
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);
        return resultMap;
    }
}
