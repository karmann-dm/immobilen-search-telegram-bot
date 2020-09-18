package com.karmanno.immobilensearchtelegrambot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/immoscout")
@Slf4j
@RequiredArgsConstructor
public class ImmoscoutController {
    private final OAuthConsumer oAuthConsumer;

    @GetMapping
    public void callbackHandler(
            @RequestParam("oauth_token") String oauthToken,
            @RequestParam("oauth_verifier") String oauthVerifier,
            @RequestParam("state") String state
    ) {
        log.info("Callback!!!!");
        String requestToken = oAuthConsumer.getToken();
        String requestTokenSecret = oAuthConsumer.getTokenSecret();
        log.info("Token: {}", requestToken);
        log.info("Token secret: {}", requestTokenSecret);
        log.info("OAuth token: {}", oauthToken);
        log.info("OAuth verifier: {}", oauthVerifier);
        log.info("State: {}", state);
    }
}
