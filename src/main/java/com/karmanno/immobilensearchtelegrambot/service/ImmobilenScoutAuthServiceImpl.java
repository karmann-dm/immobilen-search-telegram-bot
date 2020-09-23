package com.karmanno.immobilensearchtelegrambot.service;

import com.karmanno.immobilensearchtelegrambot.auth.OAuthUtils;
import com.karmanno.immobilensearchtelegrambot.dto.ImmoscoutRequestTokenResponse;
import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImmobilenScoutAuthServiceImpl implements ImmobilenScoutAuthService {
    private final RestTemplate restTemplate;
    private final ImmoscoutProperties immoscoutProperties;
    private final OAuthUtils oAuthUtils;

    @PostConstruct
    public void postConstruct() {
        this.initAuth("aaaaaaaaaa");
    }

    @Override
    public String initAuth(String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "OAuth");
        httpHeaders.addAll(oAuthUtils.generateOAuthHeadersForRequestToken(
                immoscoutProperties.getUrl().getRequestToken(),
                immoscoutProperties.getAuth().getConsumerSecret()
        ));
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        log.info(entity.getHeaders().toString());
        HttpEntity<ImmoscoutRequestTokenResponse> response = restTemplate.postForEntity(
                immoscoutProperties.getUrl().getRequestToken(),
                entity,
                ImmoscoutRequestTokenResponse.class
        );
        log.info(response.getBody().toString());
        return response.getBody().getOauthToken();
    }

    @Override
    public String getOAuthToken(String userId, String oAuthToken, String oauthVerifier) {
        return null;
    }
}
