package com.util.rabbitMQ;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class MQ_ConsumerConfig {
    //设置RabbitMQ相关信息-IP和端口
    Address[] addresses = new Address[]{
            new Address("192.168.10.248",5672)
    };

    public Connection MQ_ConsumerConfig() throws IOException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息-账号密码
        factory.setUsername("mq248");
        factory.setPassword("mq248");

        Connection connection = factory.newConnection(addresses);
        return connection;
    }
}
