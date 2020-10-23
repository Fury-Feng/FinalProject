package com.bham.project.service.impl;

import com.bham.project.dao.mapper.ApplicantMapper;
import com.bham.project.dao.mapper.UserApplicantMapper;
import com.bham.project.entity.Applicant;
import com.bham.project.entity.UserApplicant;
import com.bham.project.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/26
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private UserApplicantMapper userApplicantMapper;

    @Autowired
    private ApplicantMapper applicantMapper;
    /**
     * Create applicant data
     *
     * @param userId
     * @return
     */
    @Override
    public HashMap<String, Object> createApplicant(int userId) {
        String aid = String.valueOf(userId);
        UserApplicant userApplicant = new UserApplicant();
        userApplicant.setUserId(userId);
        userApplicant.setAid(aid);
        if (userApplicantMapper.addUserApplicant(userApplicant) == 1) {
            Applicant applicant = new Applicant();
            applicant.setAid(aid);
            if (applicantMapper.addApplicant(applicant) == 1) {
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", 200);
                resultMap.put("msg", "success");
                resultMap.put("data", aid);
                return resultMap;
            } else {
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", 404);
                resultMap.put("msg", "Fail to insert the applicant");
                return resultMap;
            }
        } else {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 404);
            resultMap.put("msg", "Fail to insert the user_applicant");
            return resultMap;
        }
    }

    /**
     * Edit the applicant information
     */
    @Override
    public HashMap<String, Object> editApplicant(int userId, HashMap<String, Object> reqParam) {
        String ugSchool = reqParam.get("ug_school").toString();
        float ugGpa = Float.parseFloat(reqParam.get("ug_gpa").toString());
        String ugMajor = reqParam.get("ug_major").toString();
        String intro = reqParam.get("personal_intro").toString();
        String aid = String.valueOf(userId);

        if (applicantMapper.editApplicant(ugSchool, ugGpa, ugMajor, intro, aid) == 1) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", aid);
            return resultMap;
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 404);
        resultMap.put("msg", "failed to edit the applicant information");
        return resultMap;
    }

    @Override
    public HashMap<String, Object> getApplicantInfo(int userID) {
        String aid = userApplicantMapper.selectByUserId(userID).getAid();
        Applicant applicant = applicantMapper.selectByAid(aid);
        HashMap<String, Object> data = new HashMap<>();
        data.put("ug_school", applicant.getUgSchool());
        data.put("ug_gpa", applicant.getUgGpa());
        data.put("ug_major", applicant.getUgMajor());
        data.put("personal_intro", applicant.getPersonalIntro());
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);
        return resultMap;
    }
}
