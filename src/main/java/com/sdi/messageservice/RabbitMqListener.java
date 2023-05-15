package com.sdi.messageservice;

import com.google.gson.Gson;
import jakarta.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitMqListener {

    Logger logger= LoggerFactory.getLogger(RabbitMqListener.class);

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
        //logger.info("test2");
        //Message message1=gson.fromJson(message,Message.class);
        logger.info("Received from jopa listener {}",message);
    }

}
