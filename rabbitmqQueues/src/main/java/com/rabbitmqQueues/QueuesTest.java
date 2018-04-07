package com.rabbitmqQueues;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class QueuesTest 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(QueuesTest.class);
    public static void main( String[] args ) throws Exception
    {
    	String[] MessageArray = new String[1000];
    	for(int i = 0 ; i <1000; i ++ ) {
    		MessageArray[i]= "MyRabitMQ_Message----" +(i+1);
    	}
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection con = factory.newConnection();
    	Channel channel = con.createChannel();
    	channel.queueDeclare("MyRabbitMQ_Queue", true, false, false, null);
    	for(int i = 0 ; i <MessageArray.length; i ++ ) {
    		channel.basicPublish("", "MyRabbitMQ_Queue", MessageProperties.PERSISTENT_TEXT_PLAIN, MessageArray[i].getBytes());
    		LOGGER.info("sent " + MessageArray[i]);
    	}
    	try {
    	    if (channel != null)
    	        //channel.close();
    	    	channel.abort();
    	    if (con != null)
    	        con.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
}
