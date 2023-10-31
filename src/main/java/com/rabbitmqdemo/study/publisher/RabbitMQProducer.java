package com.rabbitmqdemo.study.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    // переменная принимает значение с application.properties
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // переменная принимает значение с application.properties
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // создается переменная LOGGER для записи логов
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    // создается переменная rabbitTemplate
    private RabbitTemplate rabbitTemplate;
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message){
        LOGGER.info(String.format("Message -> %s", message)); // отправляет в консоль с сообщением
        // конвертирует и отправляет от exchange по ключу маршрутизатору сообщение
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
