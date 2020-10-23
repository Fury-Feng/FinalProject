package com.bham.project.service;

import com.bham.project.entity.ProjectUser;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/19
 */
public interface UserService {

    /**
     * The method is used to make user register, add the user information
     * @param registerInfo        The information to register
     * @return              the user information whether the user is inserted
     */
    public HashMap<String, Object> userRegister(HashMap<String, Object> registerInfo);

    /**
     * The method is used to login
     * @param loginInfo           The information to login
     * @return                    The information whether the user is login
     */
    public ProjectUser userLogin(HashMap<String, Object> loginInfo);

    public HashMap<String, Object> getUserInfo(String username);
}
