package com.dao.redis;

import com.dao.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

public class RedisDaoImpl implements RedisDao {

    //日志记录
    private static final Logger LOG = LoggerFactory.getLogger(RedisDaoImpl.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void insertString(Map<String, String> map) {
        jedisPool.getResource().set(map.get("name").toString(),map.get("value").toString());
    }

    @Override
    public void insertList(List list) {
        for (Object obj : list) {
            jedisPool.getResource().lpush("",obj.toString());
        }
    }

    @Override
    public void insertHash(Object object) {
        //super.getResource().hset("",object.toString());
    }

    @Override
    public void delete(String name) {
        jedisPool.getResource().del(name);
    }

    @Override
    public String selectMap(Map<String, String> map) {
        return jedisPool.getResource().hmget(map.get("name").toString(),map.get("fileName").toString()).get(0);
    }

    @Override
    public String selectString(Map<String, String> map) {
        return jedisPool.getResource().get(map.get("name"));
    }
}
