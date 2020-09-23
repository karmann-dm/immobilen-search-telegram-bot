package com.karmanno.immobilensearchtelegrambot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImmoscoutRequestTokenResponse {
    @JsonProperty("oauth_token")
    private String oauthToken;
}
