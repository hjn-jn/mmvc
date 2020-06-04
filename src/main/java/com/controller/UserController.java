package com.controller;

import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
    public class UserController extends BaseController{
    static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/selectMenuIsactive")
    public String selectMenuIsactive(){
        Map map = new HashMap();
        List list = userService.getUserByMap(map);
        return SUCCESS;
    }

    @RequestMapping("/selectAllMenu")
    public String selectAllMenu(){
        Map map = new HashMap();
        List list = userService.selectAllUsers(map);
        return SUCCESS;
    }

}
