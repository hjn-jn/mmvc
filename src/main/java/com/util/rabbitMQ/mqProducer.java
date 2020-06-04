package com.util.rabbitMQ;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * test
 */
public class mqProducer {

    static Logger log = LoggerFactory.getLogger(mqProducer.class);

    public static void main(String[] args) {
        final SortedSet<Long> ss = Collections.synchronizedSortedSet(new TreeSet<Long>());

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置RabbitMQ相关信息
        factory.setHost("192.168.10.248");
        factory.setUsername("mq248");
        factory.setPassword("mq248");
        factory.setPort(5672);

        Connection connection = null;
        try {
            //创建一个新的连接
            connection = factory.newConnection();

            //创建一个通道
            Channel channel = connection.createChannel();

            //删除队列
            channel.queueDelete("erpQueue");
            //删除交换器
            channel.exchangeDelete("erpExchange");
            /**
             * 交换机
             * exchangeDeclare(String name,
             *             String type
             *             boolean autoDelete,
             *             boolean internal,
             *             Map<String, Object> arguments
             *                      1.alternate-exchange:exchane_Name
             *                      指定备份交换器 当路由不可达就会转入此备份交换中，
             *                      如果此交换也不可达（或不存在）消息丢失，
             *                      ！！和mandatory同时存在mandatory失效
             *                      2.x-dead-letter-exchange:exchane_Name
             *                      设置死信交换器
             *                      3.x-dead-letter-routing-key:key_String
             *                      设置死信交换器路由键
             *             );
             */
            String EXCHANGE_NAME = "erpExchange";
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);

            // 声明一个队列
            /**
             * queueDeclare(String queue,
             *             boolean durable,
             *             boolean exclusive,
             *             boolean autoDelete
             *             Map<String, Object> arguments
             *                  1.x-max-priority:10
             *                  优先级:数字越高优先级越大
             *                  2.x-message-ttl:6000
             *                  设置【队列级】消息过期时间,6000毫秒单位
             *             );
             */
            String QUEUE_NAME="erpQueue";
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            //信道和交换器绑定设定路由键
            channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"test_key");
            //添加路由不可达监听，根据exchangeDeclare中参数mandatory为true时开始使用，否则无效
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode,
                                         String replyText,
                                         String exchange,
                                         String routingKey,
                                         AMQP.BasicProperties properties,
                                         byte[] body)
                        throws IOException {
                    String message = new String(body);
                    //System.out.println("in ReturnListener");
                    //System.out.println(message);
                }
            });

            //模拟查询到10条信息需要发送到队列中
            List<String> list = new ArrayList<String>();
            for (int i = 0;i<10;i++){
                String message = "info"+0+i+":信息生成时间==>"+new Date().toString();
                list.add(message);
            }

            //发送消息到队列中
            /**
             * basicPublish(
             *      String exchange_name,
             *      String routing_key,
             *      boolean Mandatory,是否可达，若不可达返回addReturnListener设置的方法来处理
             *      boolean Immediate,是否有消费存在，如果没有则返回addReturnListener设置的方法来处理
             *      AMQP.BasicProperties properties,
             *          //代码：
             *          AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
             *          builder.deliveryMode(2);//持久化
             *          builder.expiration("6000");//TTL=60000毫秒
             *          AMQP.BasicProperties properties = builder.build();
             *      Bytes[] message_String
             * )
             */
            //channel.txSelect(); //开启事务机制，处理单位为当前信道，弊端：如果未commit或者rollback，信道会阻塞！适用于单条不利于高吞吐要求
            //channel.confirmSelect();//发送方确认机制，比较信道事务机制是轻量级的，异步！若rabbitMQ内部错误会调用basicNack方法！
            System.out.println(channel.confirmSelect());
            //异步confirm
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    if(b){
                        System.out.println("handleAck"+(l+1)+":"+ss.headSet(l).toString());
                        ss.headSet(l+1).clear();
                    }else{
                        System.out.println("handleAck"+l+":");
                        ss.remove(l);
                    }
                }
                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    if(b){
                        System.out.println(l+1);
                        ss.headSet(l+1).clear();
                    }else{
                        System.out.println("handleNack:"+l);
                        ss.remove(l);
                    }
                }
            });

            for (int c=0;c<list.size();c++){
                try {
                    long nextSeqNo = channel.getNextPublishSeqNo();

                    channel.basicPublish(EXCHANGE_NAME, "test_key",null, list.get(c).getBytes("UTF-8"));
                    //channel.txCommit(); //开启事务机制-确认提交
                    ss.add(nextSeqNo);
                    TimeUnit.SECONDS.sleep(2);
                    //if (!channel.waitForConfirms()){ //发送方确认机制（同步方式） - 等待服务端确认
                    //    System.out.println("发送消息失败！");
                    //}
                }catch (IOException e){
                    log.error(e.toString());
                    //channel.txRollback(); //开启事务机制-回滚
                }


            }
            //延迟几秒

            //关闭信道和连接
            channel.close();
            connection.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
