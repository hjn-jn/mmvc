package com.dao;

import java.util.List;
import java.util.Map;

/**
 * 配置信息Dao接口
 */
public interface UserDao {
    /**
     *
     * @param map
     * @return
     */
    public List selectMenuIsActive(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    public List selectAllMenu(Map<String, Object> map);
}
