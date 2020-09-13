package com.karmanno.immobilensearchtelegrambot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.immoscout")
@Data
public class ImmoscoutProperties {
    private AuthProperties auth;
    private UrlProperties url;

    @Data
    public static class AuthProperties {
        private String consumerKey;
        private String consumerSecret;
    }

    @Data
    public static class UrlProperties {
        private String requestToken;
        private String accessToken;
        private String confirmAccess;
    }
}
