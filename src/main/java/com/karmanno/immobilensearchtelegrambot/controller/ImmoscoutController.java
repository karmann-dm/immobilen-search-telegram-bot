package com.karmanno.immobilensearchtelegrambot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/immoscout")
public class ImmoscoutController {
    @GetMapping
    public void callbackHandler() {
        System.out.println("Callback!!!!");
    }
}
