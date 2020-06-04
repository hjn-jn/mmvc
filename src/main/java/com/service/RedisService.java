package com.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
    /**
     *
     * @param map
     * @return
     */
    public String selectString(Map<String, String> map);

    /**
     *
     * @param map
     * @return
     */
    public List selectList(Map<String, Object> map);
}
