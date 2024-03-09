package com.example.malang.oauth.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocialLoginTestController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
