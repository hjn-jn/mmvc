package com.service.impl;

import com.dao.UserDao;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List getUserByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public List selectAllUsers(Map<String, Object> map) {
        return null;
    }
}
