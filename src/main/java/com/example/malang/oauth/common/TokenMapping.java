package com.example.malang.oauth.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class TokenMapping {

    private final String accessToken;
    private final String refreshToken;

    @Builder
    public TokenMapping(String accessToken , String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken =refreshToken;
    }
}
