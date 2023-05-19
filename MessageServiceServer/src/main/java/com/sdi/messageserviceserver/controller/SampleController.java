package com.sdi.messageserviceserver.controller;

import com.sdi.messageserviceserver.model.CallBack;
import com.sdi.messageserviceserver.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
        template.convertAndSend("direct", mess.getKey(), mess.getMessage()+" "+mess.getDelay()+" "+mess.getHasCallBack(), new MessagePostProcessor() {

            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
                message.getMessageProperties().setPriority(mess.getPriority());
                return message;
            }

        });
        return ResponseEntity.ok("Success emit to queue");
    }
    @PostMapping("/print")
    public ResponseEntity<String> print(@RequestBody CallBack callBack){
        logger.info(callBack.getBody());
        return ResponseEntity.ok("Success get callback");
    }
}
