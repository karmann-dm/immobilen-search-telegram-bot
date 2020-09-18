package com.karmanno.immobilensearchtelegrambot.auth;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthUtils {
    private static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
    private static final String OAUTH_SIGNATURE_DEFAULT_METHOD = "HMAC-SHA1";
    private static final String OAUTH_SIGNATURE = "oauth_signature";
    private static final String OAUTH_TIMESTAMP = "oauth_timestamp";
    private static final String OAUTH_TOKEN = "oauth_token";
    private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    private static final String OAUTH_NONCE = "oauth_nonce";
    private static final String OAUTH_VERSION = "oauth_version";
    private static final String OAUTH_DEFAULT_VERSION = "1.0";

    private final ImmoscoutProperties immoscoutProperties;
    private final OAuthConsumer oAuthConsumer;

    public Map<String, String> generateOAuthHeaders(String uri, String token) {
        Map<String, String> oAuthHeaders = new HashMap<>();
        oAuthHeaders.put(OAUTH_SIGNATURE_METHOD, OAUTH_SIGNATURE_DEFAULT_METHOD);
        oAuthHeaders.put(OAUTH_VERSION, OAUTH_DEFAULT_VERSION);
        oAuthHeaders.put(OAUTH_CONSUMER_KEY, immoscoutProperties.getAuth().getConsumerKey());
        oAuthHeaders.put(OAUTH_TOKEN, token);
        oAuthHeaders.put(OAUTH_NONCE, UUID.randomUUID().toString());
        oAuthHeaders.put(OAUTH_TIMESTAMP, Long.toString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
        oAuthHeaders.put(OAUTH_SIGNATURE, calculateSignature(oAuthHeaders, uri));

        oAuthHeaders.forEach((k, v) -> log.info("{} = {}", k, v));
        return oAuthHeaders;
    }

    private String calculateSignature(Map<String, String> oauthHeaders, String uri) {
        return new String(
                HmacUtils.hmacSha1(
                    calculateSignatureKey(),
                    calculateBaseUrlString(oauthHeaders, uri)
        ));
    }

    @SneakyThrows
    private String calculateSignatureKey() {
        return URLEncoder.encode(
                immoscoutProperties.getAuth().getConsumerSecret() + "&" + oAuthConsumer.getConsumerSecret(),
                StandardCharsets.UTF_8.toString()
        );
    }

    @SneakyThrows
    private String calculateBaseUrlString(Map<String, String> oauthHeaders, String uri) {
        URI uriObject = URI.create(uri);
        return "GET&" +
                URLEncoder.encode(
                        uriObject.getScheme() + "://" + uriObject.getAuthority() + "&",
                        StandardCharsets.UTF_8.toString()
                ) +
                URLEncoder.encode(
                        uriObject.getQuery(),
                        StandardCharsets.UTF_8.toString()
                ) +
                URLEncoder.encode(
                        oauthHeaders.entrySet()
                                .stream()
                                .map(e -> e.getKey() + "=" + e.getValue())
                                .collect(Collectors.joining("&")),
                        StandardCharsets.UTF_8.toString()
                );
    }
}
