package com.karmanno.immobilensearchtelegrambot.auth;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    private static final String OAUTH_CALLBACK = "oauth_callback";
    private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    private static final String OAUTH_NONCE = "oauth_nonce";
    private static final String OAUTH_VERSION = "oauth_version";
    private static final String OAUTH_DEFAULT_VERSION = "1.0";

    private final ImmoscoutProperties immoscoutProperties;

    @SneakyThrows
    public MultiValueMap<String, String> generateOAuthHeadersForRequestToken(String uri, String consumerToken) {
        HttpHeaders oAuthHeaders = new HttpHeaders();
        oAuthHeaders.add(OAUTH_SIGNATURE_METHOD, OAUTH_SIGNATURE_DEFAULT_METHOD);
        oAuthHeaders.add(OAUTH_VERSION, OAUTH_DEFAULT_VERSION);
        oAuthHeaders.add(OAUTH_CONSUMER_KEY, immoscoutProperties.getAuth().getConsumerKey());
        oAuthHeaders.add(OAUTH_NONCE, UUID.randomUUID().toString());
        oAuthHeaders.add(OAUTH_TIMESTAMP, Long.toString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
        oAuthHeaders.add(OAUTH_SIGNATURE, calculateSignature(oAuthHeaders, uri, consumerToken));
        oAuthHeaders.add(OAUTH_CALLBACK, URLEncoder.encode(immoscoutProperties.getUrl().getCallback(), StandardCharsets.UTF_8.toString()));
        oAuthHeaders.forEach((k, v) -> log.info("{} = {}", k, v.get(0)));
        return oAuthHeaders;
    }

    private String calculateSignature(MultiValueMap<String, String> oauthHeaders, String uri, String consumerToken) {
        return HmacUtils.hmacSha1Hex(
                calculateSignatureKey(consumerToken),
                calculateBaseUrlString(oauthHeaders, uri)
        );
    }

    @SneakyThrows
    private String calculateSignatureKey(String consumerToken) {
        return URLEncoder.encode(
                immoscoutProperties.getAuth().getConsumerSecret() + "&" + consumerToken,
                StandardCharsets.UTF_8.toString()
        );
    }

    @SneakyThrows
    private String calculateBaseUrlString(MultiValueMap<String, String> oauthHeaders, String uri) {
        URI uriObject = URI.create(uri);
        return "GET&" +
                URLEncoder.encode(
                        uriObject.getScheme() + "://" + uriObject.getAuthority() + "&",
                        StandardCharsets.UTF_8.toString()
                ) +
                (uriObject.getQuery() != null ? URLEncoder.encode(
                        uriObject.getQuery(),
                        StandardCharsets.UTF_8.toString()
                ) : "") +
                URLEncoder.encode(
                        oauthHeaders.entrySet()
                                .stream()
                                .map(e -> e.getKey() + "=" + e.getValue().get(0))
                                .collect(Collectors.joining("&")),
                        StandardCharsets.UTF_8.toString()
                );
    }
}
