package com.bham.project.controller;

import com.alibaba.fastjson.JSON;
import com.bham.project.entity.ProjectUser;
import com.bham.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/26
 */
@RestController
@ResponseBody
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Object userRegister(@RequestBody HashMap<String, Object> requestParam) {
        HashMap<String, Object> resultMap = userServiceImpl.userRegister(requestParam);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object userLogin(@RequestBody HashMap<String, Object> requestParam, HttpServletRequest request) {
        ProjectUser projectUser = userServiceImpl.userLogin(requestParam);

        if (projectUser == null) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 404);
            resultMap.put("msg", "The user is not exist");
            return resultMap;
        } else {
            if (!projectUser.getPassword().equals(requestParam.get("password"))) {
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", 405);
                resultMap.put("msg", "The password is not right");
                return resultMap;
            }

            request.getSession().setAttribute("session_user", projectUser);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "Login success");
            resultMap.put("user_id", projectUser.getUserId());
            return resultMap;
        }
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Object getUserInfo(@RequestParam("username") String username) {
        HashMap<String, Object> resultMap = userServiceImpl.getUserInfo(username);
        String jsonOutput = JSON.toJSONString(resultMap);
        return jsonOutput;
    }

}
