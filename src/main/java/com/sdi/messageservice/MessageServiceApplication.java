package com.sdi.messageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
//Создать процесс-сервер, который получает сообщения от процессов-генераторов сообщений.
// Каждое сообщение содержит параметры обработки: время обработки в секундах, признак необходимости отправки подтверждения о выполнении генератору, приоритет. --
// Сервер сообщений принимает их и ставит в очередь. --выполнено
// Существуют процессы обработчики, которые подсоединяются к серверу, получают очередную заявку на обслуживание, выполняют её и сообщают результат серверу. --выполнено
// При условии, что режим обработки заявки требует подтверждения, сервер должен послать сообщение с подтверждением соответствующему процессу генератору заявок.
// Ввести механизм приоритетов, в соответствии с которым заявки попадают в очередь. --выполнено
@EnableAutoConfiguration
@ComponentScan
@Import(RabbitAutoConfiguration.class)

public class MessageServiceApplication {

    public static void main(String[] args) {
        //while (true){
       SpringApplication.run(MessageServiceApplication.class, args);
        System.out.println("ИДИ НАХУЙ");}
   // }

}
