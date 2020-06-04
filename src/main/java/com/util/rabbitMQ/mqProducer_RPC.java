package com.util.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * test
 */
public class mqProducer_RPC {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("mq248");
        factory.setPassword("mq248");
        factory.setHost("192.168.10.248");
        factory.setPort(5672);
        Connection connection;

        final String QUEUE_NAME = "RPC_Server_Queue";
        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            //channel.exchangeDeclare("Change","direct",true,false,null);
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            channel.basicQos(1);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String s,
                                           Envelope envelope,
                                           AMQP.BasicProperties basicProperties,
                                           byte[] bytes)
                        throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties()
                            .builder()
                            .correlationId(basicProperties.getCorrelationId())
                            .build();
                    String response = "我收到信息了，返回一个消息！";
                    try {
                        String message = new String(bytes,"UTF-8");
                        System.out.println(message);
                        System.out.println("客户希望我返回的队列："+basicProperties.getReplyTo());
                    }catch (RuntimeException e){
                        System.out.println("[.]"+e.toString());
                    }finally {
                        channel.basicPublish("",basicProperties.getReplyTo(),replyProps,response.getBytes("UTF-8"));
                        channel.basicAck(envelope.getDeliveryTag(),false);
                    }
                }
            };

            channel.basicConsume(QUEUE_NAME,false,consumer);

            TimeUnit.SECONDS.sleep(1);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}