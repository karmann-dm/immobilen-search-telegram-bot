package com.karmanno.immobilensearchtelegrambot.auth;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ImmoscoutAuthConfiguration {
    private final ImmoscoutProperties immoscoutProperties;

    @Bean
    public OAuthProvider oAuthProvider(OAuthConsumer consumer) {
        return new DefaultOAuthProvider(
                immoscoutProperties.getUrl().getRequestToken(),
                immoscoutProperties.getUrl().getAccessToken(),
                immoscoutProperties.getUrl().getConfirmAccess()
        );
    }

    @Bean
    public OAuthConsumer oAuthConsumer() {
        return new DefaultOAuthConsumer(
                immoscoutProperties.getAuth().getConsumerKey(),
                immoscoutProperties.getAuth().getConsumerSecret()
        );
    }

    @Bean
    @SneakyThrows
    public String authUrl(OAuthProvider provider, OAuthConsumer consumer) {
        String authUrl = provider.retrieveRequestToken(consumer, immoscoutProperties.getUrl().getCallback());
        log.info("Auth url: {}", authUrl);
        return authUrl;
    }
}
