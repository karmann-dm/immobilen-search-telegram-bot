package com.karmanno.immobilensearchtelegrambot.controller;

import com.karmanno.immobilensearchtelegrambot.auth.OAuthUtils;
import com.karmanno.immobilensearchtelegrambot.service.ImmobilenScoutAuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/immoscout")
@Slf4j
@RequiredArgsConstructor
public class ImmoscoutController {
    private final ImmobilenScoutAuthService immobilenScoutAuthService;

    @GetMapping
    @SneakyThrows
    public void callbackHandler(
            @RequestParam("oauth_token") String oauthToken,
            @RequestParam("oauth_verifier") String oauthVerifier,
            @RequestParam("state") String state
    ) {
    }
}
