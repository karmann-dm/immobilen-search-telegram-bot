package com.karmanno.immobilensearchtelegrambot.auth;

import com.karmanno.immobilensearchtelegrambot.properties.ImmoscoutProperties;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OAuthUtilsTest {
    private static final ImmoscoutProperties immoscoutProperties = new ImmoscoutProperties()
            .setAuth(
                    new ImmoscoutProperties.AuthProperties(
                            "CONSUMER_KEY",
                            "CONSUMER_SECRET"
                    )
            )
            .setUrl(
                    new ImmoscoutProperties.UrlProperties(
                            "http://some-url/some-path",
                            "http://some-another-url/some-another-path",
                            "http://some-confirm-url/some-confirm-path",
                            "http://some-callback/some-callback-path"
                    )
            );
    private static final OAuthUtils oAuthUtils = new OAuthUtils(immoscoutProperties);

    @Test
    public void testOAuthHeaders() {
        // given:
        // when:
        Map<String, String> headers = oAuthUtils.generateOAuthHeaders(
                "http://some-request-url/some-request-path?p1=v1&p2=v2&p3=v3",
                "CONSUMER_TOKEN",
                "OAUTH_TOKEN"
        );

        // then:
        assertTrue(headers.containsKey("oauth_signature_method"));
        assertTrue(headers.containsKey("oauth_signature"));
        assertTrue(headers.containsKey("oauth_timestamp"));
        assertTrue(headers.containsKey("oauth_token"));
        assertTrue(headers.containsKey("oauth_consumer_key"));
        assertTrue(headers.containsKey("oauth_nonce"));
        assertTrue(headers.containsKey("oauth_version"));

        assertEquals(UUID.randomUUID().toString().length(), headers.get("oauth_nonce").length());
        assertEquals("HMAC-SHA1", headers.get("oauth_signature_method"));
        assertEquals("1.0", headers.get("oauth_version"));
    }
}
