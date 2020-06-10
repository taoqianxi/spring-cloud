package com.mq.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {

    public static Connection getConnection() throws Exception{
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.111.91.155");
        factory.setPort(3001);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory.newConnection();
    }

    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();
        //创建管道
        Channel channel = connection.createChannel();
        //创建对列
        channel.queueDeclare("testQueue",false,false,false,null);
        //消息内容
        for (int i = 0; i < 10000; i++) {
            String message = "Hello World" + i;
            channel.basicPublish("","testQueue",null,message.getBytes());
        }
        channel.close();
        connection.close();
    }


}
