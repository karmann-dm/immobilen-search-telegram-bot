package com.karmanno.immobilensearchtelegrambot.auth;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import lombok.RequiredArgsConstructor;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImmoscoutAuthConfiguration {
    private final ImmoscoutProperties immoscoutProperties;

    @Bean
    public OAuthProvider oAuthProvider() {
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
}
