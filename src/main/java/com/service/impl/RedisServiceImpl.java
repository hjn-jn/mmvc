package com.service.impl;

import com.dao.RedisDao;
import com.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisDao redisDao;

    @Override
    public String selectString(Map<String, String> map) {
        return redisDao.selectString(map);
    }

    @Override
    public List selectList(Map<String, Object> map) {
        return null;
    }
}
