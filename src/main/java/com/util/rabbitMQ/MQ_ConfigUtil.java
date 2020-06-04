package com.util.rabbitMQ;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Map;

/**
 * MQ配置工具
 * 负责增删改队列，交换器，绑定
 */
public class MQ_ConfigUtil extends MQ_ProducerConfig{

    /**
     * 创建队列
     * @param queueName
     * @param durable
     * @param exclusive
     * @param autoDelete
     * @param arguments
     */
    public void createQueue(String queueName, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments ){
        Channel channel = null;
        try {
            channel = super.getProducerConnection().createChannel();
            channel.queueDeclare(queueName,durable,exclusive,autoDelete,arguments);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(1==1){

            }
        }
    }

    /**
     * 删除队列
     * @param queueName
     */
    public void deleteQueue(String queueName){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.queueDelete(queueName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建交换器
     * @param exchangeName
     */
    public void createExchange(String exchangeName){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.exchangeDelete(exchangeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除交换器
     * @param exchangeName
     */
    public void deleteExchange(String exchangeName){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.exchangeDelete(exchangeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 队列绑定
     * @param queueName
     * @param exchangeName
     * @param routingKey
     */
    public void queueBind(String queueName,String exchangeName,String routingKey){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.queueUnbind(queueName,exchangeName,routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 队列去除绑定
     * @param queueName
     * @param exchangeName
     * @param routingKey
     */
    public void queueUnbind(String queueName,String exchangeName,String routingKey){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.queueUnbind(queueName,exchangeName,routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 交换器绑定
     * @param exchangeSource
     * @param exchangeName
     * @param routingKey
     */
    public void exchangeBind(String exchangeSource,String exchangeName,String routingKey){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.exchangeBind(exchangeSource,exchangeName,routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 交换器去除绑定
     * @param exchangeSource
     * @param exchangeName
     * @param routingKey
     */
    public void exchangeUnbind(String exchangeSource,String exchangeName,String routingKey){
        try {
            Channel channel = super.getProducerConnection().createChannel();
            channel.exchangeBind(exchangeSource,exchangeName,routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
