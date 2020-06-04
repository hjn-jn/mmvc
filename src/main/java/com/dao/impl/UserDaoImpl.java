package com.dao.impl;

import com.dao.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private SqlSessionTemplate sqlSessionFactory;

    @Override
    public List selectMenuIsActive(Map<String, Object> map) {
        return sqlSessionFactory.selectList("config.selectMenuIsActive", map);
    }

    @Override
    public List selectAllMenu(Map<String, Object> map) {
        return sqlSessionFactory.selectList("config.selectAllMenu", map);
    }
}
