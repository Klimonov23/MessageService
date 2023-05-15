package com.sdi.messageservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitConfig {

    Logger logger=  LoggerFactory.getLogger(RabbitConfig.class);
    @Bean
    public ConnectionFactory connectionFactory(){return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin( connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }


    @Bean
    public Queue myQueue1(){
        //Map<String, Object> args= new HashMap<>();
        //args.put("x-max-priority", 10);
       // return new Queue("myQueue1", false, false, false, args);
        return new Queue("myQueue1");
    }

    @Bean
    public Queue myQueue2(){
        return new Queue("myQueue2");
    }

    @Bean
    public Queue priorityQueue(){
        Map<String, Object> args= new HashMap<>();
        args.put("x-max-priority", 10);
         return new Queue("priorityQueue", false, false, false, args);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("error");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("info");
    }

    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("warning");
    }

    @Bean Binding binding4(){
        return BindingBuilder.bind(priorityQueue()).to(directExchange()).with("priority");

    }
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("myQueue");
//        container.setMessageListener(message -> logger.info("Received from Queue"+new String(message.getBody())));
//        return container;
//    }
}

