package com.service.impl;

import com.dao.ConfigDao;
import com.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public List selectMenuIsActive(Map<String, Object> map) {
        return configDao.selectMenuIsActive(map);
    }

    @Override
    public List selectAllMenu(Map<String, Object> map) {
        return configDao.selectAllMenu(map);
    }
}
