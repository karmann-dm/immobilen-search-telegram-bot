package com.karmanno.immobilensearchtelegrambot;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        ImmoscoutProperties.class
})
public class ImmobilenSearchTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmobilenSearchTelegramBotApplication.class, args);
    }
}
