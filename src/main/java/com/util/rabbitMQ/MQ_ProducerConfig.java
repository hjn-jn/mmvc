package com.util.rabbitMQ;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * MQ生产者连接配置
 */
public class MQ_ProducerConfig {

    //连接工厂
    public static ConnectionFactory factory;

    /**
     * 默认
     * @throws IOException
     */
    public void MQ_ProducerConfig() throws IOException {
        //创建连接工厂
        factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("192.168.10.248");
        factory.setUsername("mq248");
        factory.setPassword("mq248");
        factory.setPort(5672);

    }

    /**
     * 定制连接
     * @param host
     * @param userName
     * @param password
     * @param port
     * @throws IOException
     */
    public static void MQ_ProducerConfig(String host,String userName,String password,int port) throws IOException {
        //创建连接工厂
        factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(host);
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setPort(port);
    }

    /**
     * 获得新连接
     * @return Connection
     * @throws IOException
     */
    public static Connection getProducerConnection() throws IOException {
        return factory.newConnection();
    }

}
