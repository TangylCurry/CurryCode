package com.rabbitmqQueues;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Subscribe {
	private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);
	private static final String EXCHANGE_NAME = "messageExchange";
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection con = factory.newConnection();
    	final Channel channel = con.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    	String queueName = channel.queueDeclare().getQueue();
    	channel.queueBind(queueName, EXCHANGE_NAME, "");
    	
    	channel.basicQos(1);
    	final Consumer consumer = new DefaultConsumer(channel) {
    		@Override
    		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties pro,byte[] body) throws IOException {
    			String message = new String(body, "utf-8");
    			LOGGER.info("Received---- "+message);
    		}
    	};
    	channel.basicConsume(queueName, true,consumer);
    	
    	
	}

}
