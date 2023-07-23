package com.example.messageserviceconsumer.listener;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.imageio.IIOException;
import java.io.IOException;

@Component
@EnableRabbit
public class Listener {

    Logger logger= LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = "myQueue1")
    public void processMyQueue(String key, String message){
        logger.info(key);
        //Message message1=gson.fromJson(message,Message.class);
        logger.info("Received from first listener {}",message);
    }

    @RabbitListener(queues = "myQueue2")
    public void processMyQueue2(String key,String message){
        //logger.info("test2");
        //Message message1=gson.fromJson(message,Message.class);
        logger.info("Received from second listener {}",message);
    }

    @RabbitListener(queues = "priorityQueue")
    public void processPriorityQueue(String key,String message) throws InterruptedException {
        String[] strings=message.split(" ");
        Thread.sleep(Integer.parseInt(strings[1]));
        logger.info("Received from priority queue's listener {}",message);
        boolean hasCallBack=Boolean.parseBoolean(strings[2]);
        try{
        if (hasCallBack){
            final Content postResult = Request.Post("http://server:8080/print")
                    .bodyString("{\"title\": \"callback\",\"body\":\"Message has processed successfully\"}", ContentType.APPLICATION_JSON)
                    .execute().returnContent();
        }}
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

}