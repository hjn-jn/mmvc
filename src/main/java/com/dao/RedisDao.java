package com.dao;

import java.util.List;
import java.util.Map;

public interface RedisDao {
    public void insertString(Map<String,String> map);

    public void insertList(List list);

    public void insertHash(Object object);

    public void delete(String name);

    public String selectMap(Map<String, String> map);

    public String selectString(Map<String, String> map);
}
