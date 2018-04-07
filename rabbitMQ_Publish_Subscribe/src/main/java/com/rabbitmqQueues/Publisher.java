package com.rabbitmqQueues;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Publisher 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);
	private static final String EXCHANGE_NAME = "messageExchange";
    public static void main( String[] args ) throws Exception
    {
    	String[] MessageArray = new String[1000];
    	for(int i = 0 ; i <1000; i ++ ) {
    		MessageArray[i]= "the publiash_Message----" +(i+1);
    	}
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection con = factory.newConnection();
    	Channel channel = con.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//定义交换机
    	
    	for(int i = 0 ; i <MessageArray.length; i ++ ) {
    		channel.basicPublish(EXCHANGE_NAME, "", null, MessageArray[i].getBytes());
    		LOGGER.info("publish message to exchange  " + MessageArray[i]);
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
