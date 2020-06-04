package com.controller;

import com.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/config")
    public class ConfigController extends BaseController{
    static Logger log = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping("/selectMenuIsActive")
    public String selectMenuIsActive(){
        Map map = new HashMap();
        try {
            List list = configService.selectMenuIsActive(map);
            System.out.println(list.isEmpty());
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @RequestMapping("/selectAllMenu")
    public String selectAllMenu(){
        Map map = new HashMap();
        try {
            List list = configService.selectAllMenu(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
