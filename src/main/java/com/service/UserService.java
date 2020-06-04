package com.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 根据条件查询用户
     * @param map
     * @return
     */
    public List getUserByMap(Map<String, Object> map);

    /**
     * 查询所有用户
     * @param map
     * @return
     */
    public List selectAllUsers(Map<String, Object> map);
}
