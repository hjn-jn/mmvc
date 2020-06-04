package com.util.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 添加确认监听需要开启确认监听模式 实现 addConfirmListener方法
 * confirm模式最大的好处在于他是异步的，一旦发布一条消息，
 * 生产者应用程序就可以在等信道返回确认的同时继续发送下一条消息，
 * 当消息最终得到确认之后，生产者应用便可以通过回调方法来处理该确认消息，
 * 如果RabbitMQ因为自身内部错误导致消息丢失，就会发送一条nack消息，生产者应用程序同样可以在回调方法中处理该nack消息；
 */
public class mqTXTest {


    public static void main(String[] args) throws Exception {


        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.10.248");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPassword("mq248");
        connectionFactory.setUsername("mq248");

        //2 获取Connection
        Connection connection = connectionFactory.newConnection();

        //3 通过Connection创建一个新的Channel
        Channel channel = connection.createChannel();


        //4 指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();
        String exchangeName = "erpExchange";
        String routingKey = "test_key1";

        //5 发送一条消息
        String msg = "Hello RabbitMQ Send confirm message!";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        // 添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println(deliveryTag);
                System.err.println("-------no ack!-----------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------ack!-----------");
            }
        });





    }
}