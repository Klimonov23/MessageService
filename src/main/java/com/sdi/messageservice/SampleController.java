package com.sdi.messageservice;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class SampleController {

    Logger logger=  LoggerFactory.getLogger(SampleController.class);

    private final RabbitTemplate template;


    public SampleController(RabbitTemplate template) {
        this.template = template;

    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody Message mess){
        logger.info("Emit to Queue");
        template.setExchange("direct");

        //logger.info(message);

//        template.convertAndSend(,mess.getKey(), ,message -> {
//            message.getMessageProperties().setPriority(mess.getPriority());
//            return message;
//        });
        template.convertAndSend("direct", mess.getKey(), mess.getMessage()+" "+mess.delay, new MessagePostProcessor() {

            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
                message.getMessageProperties().setPriority(mess.getPriority());
                return message;
            }

        });
        return ResponseEntity.ok("Success emit to queue");
    }
}
