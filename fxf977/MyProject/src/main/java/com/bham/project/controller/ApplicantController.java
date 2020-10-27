package com.bham.project.controller;

import com.alibaba.fastjson.JSON;
import com.bham.project.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/26
 */
@RestController
@ResponseBody
@CrossOrigin
public class ApplicantController {

    @Autowired
    private ApplicantService applicantServiceImpl;

    @RequestMapping(value = "/applicant/create", method = RequestMethod.POST)
    public Object createApplicant(@RequestParam("user_id") int userId) {
        HashMap<String, Object> resultMap = applicantServiceImpl.createApplicant(userId);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

    @RequestMapping(value = "/applicant/edit", method = RequestMethod.POST)
    public Object editApplicant(@RequestParam("user_id") int userId,
                                @RequestBody HashMap<String, Object> reqParam) {
        HashMap<String, Object> resultMap = applicantServiceImpl.editApplicant(userId, reqParam);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

    @RequestMapping(value = "/applicant/info", method = RequestMethod.GET)
    public Object getApplicantInfo(@RequestParam("user_id") int userId) {
        HashMap<String, Object> resultMap = applicantServiceImpl.getApplicantInfo(userId);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }
}
