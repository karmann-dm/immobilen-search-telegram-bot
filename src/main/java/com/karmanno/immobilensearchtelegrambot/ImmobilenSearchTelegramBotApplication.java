package com.karmanno.immobilensearchtelegrambot;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({
        ImmoscoutProperties.class
})
public class ImmobilenSearchTelegramBotApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ImmobilenSearchTelegramBotApplication.class, args);
    }
}
