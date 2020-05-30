package com.selenium.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.selenium.app.db.DatabaseQueryExecutor;
import com.selenium.app.models.Article;

import java.nio.charset.StandardCharsets;

public class Listener {

    private final static String EXCHANGE_NAME = "articles_queue";

    public static void main(String[] args) throws Exception {
        DatabaseQueryExecutor database = new DatabaseQueryExecutor();
        ObjectMapper objectMapper = new ObjectMapper();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            Article article = objectMapper.readValue(message, Article.class);
            database.save(article);
            System.out.println(article.toString());
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
