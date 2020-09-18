package com.karmanno.immobilensearchtelegrambot.controller;

import com.karmanno.immobilensearchtelegrambot.ImmoscoutOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
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
    private final OAuthProvider oAuthProvider;
    private final ImmoscoutOAuthService immoscoutOAuthService;

    @GetMapping
    @SneakyThrows
    public void callbackHandler(
            @RequestParam("oauth_token") String oauthToken,
            @RequestParam("oauth_verifier") String oauthVerifier,
            @RequestParam("state") String state
    ) {
        oAuthProvider.retrieveAccessToken(oAuthConsumer, oauthVerifier);
        immoscoutOAuthService.search();
    }
}
