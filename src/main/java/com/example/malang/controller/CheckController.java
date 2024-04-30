package com.example.malang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @Value("${S3_ACCESS_KEY}")
    public String accessKey;

    @Value("${DB_USERNAME}")
    public String dbUserName;

    @GetMapping("/checks")
    public String check() {
        return "accessKey : " + accessKey + " dbUserName : " + dbUserName;
    }
}
