package com.bham.project.service;

import com.bham.project.entity.ProjectUser;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/26
 */
public interface ApplicantService {

    /**
     * Create applicant data
     * @param userId
     * @return
     */
    public HashMap<String, Object> createApplicant(int userId);

    /**
     * Edit the applicant information
     */
    public HashMap<String, Object> editApplicant(int userId, HashMap<String, Object> reqParam);

    public HashMap<String, Object> getApplicantInfo(int userID);

}
