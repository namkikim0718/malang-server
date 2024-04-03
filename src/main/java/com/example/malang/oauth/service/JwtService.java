package com.example.malang.oauth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.malang.oauth.common.TokenMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    private final String PREFIX = "Bearer ";
    private final String BLANK = "";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.expiration}")
    private long accessTokenValidationSeconds;

    @Value("${jwt.refresh.expiration}")
    private long refreshTokenValidationSeconds;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    /**
     * TokenMapping 객체를 만들어서 반환합니다.
     */

    public TokenMapping createToken(String subject) {
        return TokenMapping.builder()
                .accessToken(createAccessToken(subject))
                .refreshToken(createRefreshToken())
                .build();
    }

    /**
     * JWT 토큰을 만듭니다.
     * 요청 헤더에는 "Bearer " 다음에 AccessToken 이 나오기 때문에 PREFIX.concat() 을 사용합니다.
     * RefreshToken 도 같은 맥락입니다.
     */
    public String createAccessToken(String subject) {
        return JWT.create()
                .withSubject("AccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis()+accessTokenValidationSeconds+1000))
                .withClaim("subject",subject)
                .sign(Algorithm.HMAC512(secret));
    }

    public String createRefreshToken() {
        return JWT.create()
                .withSubject("RefreshToken")
                .withExpiresAt(new Date(System.currentTimeMillis()+accessTokenValidationSeconds+1000))
                .sign(Algorithm.HMAC512(secret));
    }

    /**
     * Token 들을 요청 헤더에 담을 수 있는 메서드를 사용하기 위해 만들었습니다.
     */
    public void sendBothToken(HttpServletResponse response, String accessToken , String refreshToken) {
        setAccessTokenInHeader(response,accessToken);
        setRefreshTokenInHeader(response,refreshToken);
    }


    // 담아서 토큰 전송
    public void setAccessTokenInHeader(HttpServletResponse response , String accessToken) {
        response.setHeader(accessHeader, accessToken);
    }

    public void setRefreshTokenInHeader(HttpServletResponse response , String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }


    /**
     * 헤더에서 토큰을 추출합니다.
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        /**
         * request 에서 Authorization 를 Header 로 가지는 값을 가져오고 "Bearer " 로 시작하는 값들을 가져온다.
         * Bearer 부분을 BLANK 로 해주면서 AccessToken 의 값만 가지고 올 수 있도록 한다.
         */
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(PREFIX))
                .map(accessToken -> accessToken.replace(PREFIX,BLANK));
    }

    /**
     * extractAccessToken 과 같은 맥락이며, 여기서는 RefreshToken 을 추출합니다.
     */
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(PREFIX))
                .map(refreshToken -> refreshToken.replace(PREFIX,BLANK));
    }

    /**
     * JWT 안에 있는 email 값을 추출할 수 있도록 한다.
     * JWT 를 email 로 만들었기 떄문에 디코딩 하게 되면 email 을 추출할 수 있다.
     */
    public String extractMemberEmail(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token.replace(PREFIX, BLANK))
                .getClaim("subject")
                .asString();
    }

    /**
     * 토큰의 유효성을 검사합니다.
     */
    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }


}
