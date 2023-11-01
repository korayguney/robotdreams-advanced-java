package com.robotdreams.notification;

import com.robotdreams.notification.config.NotificationConfig;
import com.robotdreams.notification.model.Notification;
import com.robotdreams.rabbitmq.RabbitMQMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.robotdreams.notification",
                "com.robotdreams.rabbitmq"
        }
)
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }


    @Autowired
    RabbitMQMessageProducer producer;

    @Autowired
    NotificationConfig config;

    @Override
    public void run(String... args) throws Exception {
        // producer.publish("Test Test...", config.getNotificationExchange(), config.getNotificationRoutingKey());
        producer.publish(Notification.builder()
                        .toCustomerPhone("1111111")
                        .sender("aaaa")
                        .build(),
                config.getNotificationExchange(),
                config.getNotificationRoutingKey());
    }

}
