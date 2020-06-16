package com.mq.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer1 {

    public static void main(String[] args) throws Exception {
        Connection connection = Publisher.getConnection();
        Channel channel = connection.createChannel();
        //声明通道
        channel.queueDeclare("exchangeDirect",false,false,false,null);
        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume("exchangeDirect",true,consumer);
        while(true){
            //这个方法会阻塞住，直到获取到消息
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("接收到消息："+message);
        }

    }
}
