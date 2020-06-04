package com.dao;

import java.util.List;
import java.util.Map;

/**
 * 配置信息Dao接口
 */
public interface ConfigDao {
    /**
     * 查询可用菜单
     * @param map
     * @return
     */
    public List selectMenuIsActive(Map<String,Object> map);

    /**
     * 查询所有菜单
     * @param map
     * @return
     */
    public List selectAllMenu(Map<String,Object> map);
}
