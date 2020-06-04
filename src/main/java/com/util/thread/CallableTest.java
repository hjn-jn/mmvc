package com.util.thread;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableTest implements Callable<Object> {
    private String name;
    private String fileName;
    private String result;

    public CallableTest(String name,String fileName){
        this.name = name;
        this.fileName = fileName;
    }

    @Override
    public String call() throws Exception {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.10.247",6379);
        String value = jedis.hmget(name, fileName).get(0);
        return value;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.247",6379);
        String value = jedis.hmget("20180101", "tot_amt_actual").get(0);
        System.out.println(value);
    }
}
