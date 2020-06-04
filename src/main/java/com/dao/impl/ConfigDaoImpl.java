package com.dao.impl;

import com.dao.ConfigDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ConfigDaoImpl implements ConfigDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List selectMenuIsActive(Map<String, Object> map) {
        return sqlSessionTemplate.selectList("config.selectMenuIsActive", map);
    }

    @Override
    public List selectAllMenu(Map<String, Object> map) {
        return sqlSessionTemplate.selectList("config.selectAllMenu", map);
    }
}
