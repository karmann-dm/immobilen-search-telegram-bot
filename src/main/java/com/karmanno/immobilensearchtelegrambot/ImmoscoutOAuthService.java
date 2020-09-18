package com.karmanno.immobilensearchtelegrambot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImmoscoutOAuthService {

    private final OAuthConsumer consumer;

    @SneakyThrows
    public void search() {
        String url = "https://rest.immobilienscout24.de/restapi/api/search/v1.0/search/radius?realestatetype=ApartmentRent&geocoordinates=52.512303;13.431191;1&username=karmanno";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        consumer.sign(connection);
        connection.connect();
        log.info("Response: " + connection.getResponseCode() + " " + connection.getResponseMessage());
    }
}
