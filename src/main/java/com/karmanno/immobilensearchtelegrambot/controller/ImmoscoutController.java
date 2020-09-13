package com.karmanno.immobilensearchtelegrambot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/immoscout")
@Slf4j
@RequiredArgsConstructor
public class ImmoscoutController {
    private final OAuthConsumer oAuthConsumer;

    @GetMapping
    public void callbackHandler() {
        log.info("Callback!!!!");
        String requestToken = oAuthConsumer.getToken();
        String requestTokenSecret = oAuthConsumer.getTokenSecret();
        log.info("Token: {}", requestToken);
        log.info("Token secret: {}", requestTokenSecret);
    }
}
