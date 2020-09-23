package com.karmanno.immobilensearchtelegrambot.service;

public interface ImmobilenScoutAuthService {
    String initAuth(String userId);
    String getOAuthToken(String userId, String oAuthToken, String oauthVerifier);
}
