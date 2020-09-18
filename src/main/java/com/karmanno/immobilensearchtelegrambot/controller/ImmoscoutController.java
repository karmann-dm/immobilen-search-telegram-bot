package com.karmanno.immobilensearchtelegrambot.controller;

import com.karmanno.immobilensearchtelegrambot.auth.OAuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
    private final OAuthUtils oAuthUtils;

    @GetMapping
    @SneakyThrows
    public void callbackHandler(
            @RequestParam("oauth_token") String oauthToken,
            @RequestParam("oauth_verifier") String oauthVerifier,
            @RequestParam("state") String state
    ) {
        oAuthUtils.generateOAuthHeaders(
                "https://rest.immobilienscout24.de/restapi/api/search/v1.0/search/radius?realestatetype=ApartmentRent&geocoordinates=52.512303;13.431191;1",
                oauthToken
        );
    }
}
