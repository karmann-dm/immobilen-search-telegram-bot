package com.karmanno.immobilensearchtelegrambot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.telegram")
@Data
public class TelegramProperties {
    private String botKey;
}
