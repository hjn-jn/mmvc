package com.service;

import java.util.List;
import java.util.Map;

public interface ConfigService {
    /**
     *
     * @param map
     * @return
     */
    public List selectMenuIsActive(Map<String,Object> map);

    /**
     *
     * @param map
     * @return
     */
    public List selectAllMenu(Map<String,Object> map);
}
