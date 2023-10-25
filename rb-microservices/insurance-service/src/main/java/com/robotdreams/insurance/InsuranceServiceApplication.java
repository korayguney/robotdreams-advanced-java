package com.robotdreams.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(
        scanBasePackages = {
                "com.robotdreams.insurance",
                "com.robotdreams.rabbitmq"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = {
                "com.robotdreams.clients.validation",
                "com.robotdreams.clients.notification"
        }
)
public class InsuranceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
