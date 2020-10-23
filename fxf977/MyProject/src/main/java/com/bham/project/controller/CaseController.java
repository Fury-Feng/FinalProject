package com.bham.project.controller;

import com.alibaba.fastjson.JSON;
import com.bham.project.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/24
 */
@RestController
@ResponseBody
@CrossOrigin
public class CaseController {
    @Autowired
    private CaseService caseServiceImpl;

    @RequestMapping(value = "/cases", method = RequestMethod.GET)
    public Object getCases(@Nullable @RequestParam(value = "school_name") String schoolName,
                           @Nullable @RequestParam(value = "major_name") String majorName) {
        if (schoolName == null && majorName == null) {
            HashMap<String, Object> resultMap = caseServiceImpl.getAllCases();
            String jsonOutput = JSON.toJSONString(resultMap);
            return jsonOutput;
        }

        if (schoolName != null && majorName == null) {
            HashMap<String, Object> resultMap = caseServiceImpl.getCasesBySchool(schoolName);
            String jsonOutput = JSON.toJSONString(resultMap);
            return jsonOutput;
        }

        if (schoolName == null && majorName != null) {
            HashMap<String, Object> resultMap = caseServiceImpl.getCasesByMajor(majorName);
            String jsonOutput = JSON.toJSONString(resultMap);
            return jsonOutput;
        }

        if (schoolName != null && majorName != null) {
            HashMap<String, Object> resultMap = caseServiceImpl.getCases(schoolName, majorName);
            String jsonOutput = JSON.toJSONString(resultMap);
            return jsonOutput;
        }

        HashMap<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", 404);
        errorMap.put("msg", "Failed to implement the interface");
        String jsonOutput = JSON.toJSONString(errorMap);
        return jsonOutput;
    }
    @RequestMapping(value = "/cases/instance", method = RequestMethod.GET)
    public Object getInstanceCase(@RequestParam(value = "case_id") Integer caseId) {
        HashMap<String, Object> resultMap = caseServiceImpl.getCaseInstance(caseId);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

    @RequestMapping(value = "/cases/instance", method = RequestMethod.POST)
    public Object addInstanceCase(@RequestBody HashMap<String, Object> caseInfo) {
        HashMap<String, Object> resultMap = caseServiceImpl.addCase(caseInfo);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }
}
