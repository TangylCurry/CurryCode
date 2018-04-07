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

public class Worker {
	private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection con = factory.newConnection();
    	final Channel channel = con.createChannel();
    	channel.queueDeclare("MyRabbitMQ_Queue", true, false, false, null);
    	channel.basicQos(1);
    	final Consumer consumer = new DefaultConsumer(channel) {
    		@Override
    		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties pro,byte[] body) throws IOException {
    			String message = new String(body, "utf-8");
    			LOGGER.info("Received---- "+message);
    			
    			/*try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					LOGGER.info("---sleep 1 s");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}*/
    			
    			
    		}
    	};
    	channel.basicConsume("MyRabbitMQ_Queue", false,consumer);
    	
    	
	}

}
