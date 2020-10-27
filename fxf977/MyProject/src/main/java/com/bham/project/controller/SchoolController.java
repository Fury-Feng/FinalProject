package com.bham.project.controller;

import com.alibaba.fastjson.JSON;
import com.bham.project.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/25
 */
@RestController
@ResponseBody
@RequestMapping(value = "/school")
@CrossOrigin
public class SchoolController {
    @Autowired
    private SchoolService schoolServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public Object getSchoolDetail(@RequestParam("school_name") String schoolName,
                                  @Nullable @RequestParam("info_module") String infoModule) {
        HashMap<String, Object> resultMap = schoolServiceImpl.getSchoolDetails(schoolName, infoModule);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getSchoolList(@Nullable @RequestParam(value = "list_name") String listName,
                                @RequestParam(value = "page") int page,
                                @RequestParam(value = "size") int size) {
        if (listName == null) {
            HashMap<String, Object> resultMap = schoolServiceImpl.getSchoolRankTable("QSRank", page, size);
            String jsonOutput = JSON.toJSONString(resultMap);
            return jsonOutput;
        } else {
            HashMap<String, Object> resultMap = schoolServiceImpl.getSchoolRankTable(listName, page, size);
            String jsonOutput = JSON.toJSONString(resultMap);
            return jsonOutput;
        }
    }

    @RequestMapping(value = "/major", method = RequestMethod.GET)
    public Object getSchoolMajor(@RequestParam("school_name") String schoolName,
                                 @Nullable @RequestParam("major_name") String majorName) {
        HashMap<String, Object> resultMap = schoolServiceImpl.getSchoolMajor(schoolName, majorName);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getSchoolInfo(@RequestParam("school_name") String schoolName) {
        HashMap<String, Object> resultMap = schoolServiceImpl.getSchoolInfo(schoolName);
        String jsonOutPut = JSON.toJSONString(resultMap);
        return jsonOutPut;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    @ResponseBody
    public Object getSchoolApply(@RequestParam("school_name") String schoolName) {
        HashMap<String, Object> resultMap = schoolServiceImpl.getSchoolApplications(schoolName);
        String jsonOutPut = JSON.toJSONString(resultMap);
        return jsonOutPut;
    }

    @RequestMapping(value = "/alumnus", method = RequestMethod.GET)
    @ResponseBody
    public Object getSchoolAlumnus(@RequestParam("school_name") String schoolName) {
        HashMap<String, Object> resultMap = schoolServiceImpl.getAlumnus(schoolName);
        String jsonOutPut = JSON.toJSONString(resultMap);
        return jsonOutPut;
    }


}
