package com.util.rabbitMQ;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * test
 */
public class mqConsumer_RPC {

    private static String pn = "mq248";

    private static String requestQueueName = "RPC_Server_Queue";
    private static String replyQueueName = "";//Consumer_RPC

    public static void main(String[] args) {
        Address[] addresses = new Address[]{
                new Address("192.168.10.248",5672)
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPassword(pn);
        factory.setUsername(pn);
        try {
            Connection connection = factory.newConnection(addresses);
            final Channel channel = connection.createChannel();
            //channel.queueDeclare(replyQueueName,true,false,false,null);
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException {
                    System.out.println("recv message:"+new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            replyQueueName = channel.queueDeclare().getQueue();
            channel.basicConsume(replyQueueName,true,consumer);
            //TimeUnit.SECONDS.sleep(5);
            String corrId = UUID.randomUUID().toString();
            //System.out.println(corrId);
            AMQP.BasicProperties properties = new AMQP.BasicProperties()
                    .builder()
                    .correlationId(corrId)
                    .replyTo(replyQueueName)
                    .build();
            String message = "我来了！RPC_server";
            channel.basicPublish("",requestQueueName,properties,message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
