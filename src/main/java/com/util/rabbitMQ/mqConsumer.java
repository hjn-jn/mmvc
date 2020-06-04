package com.util.rabbitMQ;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * test
 */
public class mqConsumer{
    static Logger log = LoggerFactory.getLogger(mqConsumer.class);

    public static void main(String[] args) {
        Address[] addresses = new Address[]{
            new Address("192.168.10.248",5672)
        };

        String QUEUE_NAME="erpQueue";

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setUsername("mq248");
        factory.setPassword("mq248");

        Connection connection = null;
        try {
            //创建一个新的连接
            connection = factory.newConnection(addresses);
            //创建一个通道
            final Channel channel = connection.createChannel();
            //设置最多接受未被ack的消息个数
            channel.basicQos(64);
            /** 创建消费者-推模式
             *  多条接受，受basicQos限制
             */
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException {
                    System.out.println(new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //反馈给MQ确认消息
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    /**单条反馈给MQ拒绝消息
                     * basicReject(
                     *      消息编号，
                     *      是否让MQ服务器保留消息
                     * )
                     */
                    //channel.basicReject(envelope.getDeliveryTag(),true);
                    /**多条反馈给MQ拒绝消息
                     * basicNack(
                     *      消息编号，
                     *      是否多条记录 设置成false 该方法等同 basicReject()
                     *      是否让MQ服务器保留消息
                     * )
                     */
                     //channel.basicNack(envelope.getDeliveryTag(),true,true);
                }
            };
            channel.basicConsume(QUEUE_NAME,consumer);
            /** 创建消费者-拉模式
             *  一次只拉一条
             *  ！！不用while来操作影响性能
             */
//            GetResponse response = channel.basicGet(QUEUE_NAME,false);
//            System.out.println(new String(response.getBody()));
//            channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
            //延迟5秒
            TimeUnit.SECONDS.sleep(5);
            //关闭通道和连接
            channel.close();
            connection.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
