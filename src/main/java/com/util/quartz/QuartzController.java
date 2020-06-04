package com.util.quartz;

import com.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class QuartzController {

    public void resetConfigInformation(){

        System.out.println("resetConfigInformation");
    }

    public void stop(){
        System.out.println("test_stop");
    }

}
