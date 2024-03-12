package com.example.malang.oauth.handler;

import com.example.malang.domain.member.Member;
import com.example.malang.domain.member.OAuth2ProviderMember;
import com.example.malang.domain.member.PrincipalMember;
import com.example.malang.domain.member.ProviderMember;
import com.example.malang.oauth.common.TokenMapping;
import com.example.malang.oauth.service.JwtService;
import com.example.malang.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String TOKEN = "token";
    private static final String REFRESH_TOKEN = "refreshToken";

    private static final String REDIRECT_URL = "http://localhost:3000/address";

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        /**
         * 토큰을 받고 그 토큰 값을 프론트에게 Redirect 시키면서 넘깁니다.
         */
        TokenMapping tokenMapping = saveMember(authentication);
        getRedirectStrategy().sendRedirect(request,response,getRedirectUrl(tokenMapping));

    }

    /**
     * 인증에 성공한 Member 에게는 JWT 를 만들어서 반환합니다.
     * 해당 서비스에서는 TokenMapping 를 반환합니다.
     */
    private TokenMapping saveMember(Authentication authentication) {

        PrincipalMember principalMember = (PrincipalMember) authentication.getPrincipal();

        String email = principalMember.providerMember().getEmail();
        TokenMapping token = jwtService.createToken(email);

        Member findMember = memberRepository.findByEmail(email).get();
        findMember.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private String getRedirectUrl(TokenMapping token) {
        /**
         * 프론트엔드에게 넘길때 쿼리 파라미터로 담아서 같이 넘깁니다.
         */
        return UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam(TOKEN, token.getAccessToken())
                .queryParam(REFRESH_TOKEN, token.getRefreshToken())
                .build().toUriString();
    }


}
