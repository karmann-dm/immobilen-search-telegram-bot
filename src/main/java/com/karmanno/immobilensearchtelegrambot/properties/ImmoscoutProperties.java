package com.karmanno.immobilensearchtelegrambot.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.immoscout")
@Data
public class ImmoscoutProperties {
    private AuthProperties auth;
    private UrlProperties url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthProperties {
        private String consumerKey;
        private String consumerSecret;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UrlProperties {
        private String requestToken;
        private String accessToken;
        private String confirmAccess;
        private String callback;
    }
}
