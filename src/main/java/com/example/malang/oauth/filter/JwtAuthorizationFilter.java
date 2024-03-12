package com.example.malang.oauth.filter;

import com.example.malang.domain.member.Member;
import com.example.malang.domain.member.PrincipalMember;
import com.example.malang.domain.member.social.ContextMember;
import com.example.malang.domain.member.social.KakaoMember;
import com.example.malang.oauth.common.converters.ProviderMemberRequest;
import com.example.malang.oauth.service.JwtService;
import com.example.malang.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
/**
 * OncePerRequestFilter : HttpRequest 의 한 번의 요청에 대해 한 번만 실행한다.
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    /**
     * accessToken 유효 -> authentication 저장
     * accessToken 만료
     *      refreshToken 유효 -> authentication 저장, accessToken 갱신
     *      refreshToken 만료 -> authentication 저장 X
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         *
         */
        jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .ifPresentOrElse(
                        this::saveAuthentication,
                        () -> checkRefreshToken(request, response)
                );
        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(String accessToken) {
        String email = jwtService.extractMemberEmail(accessToken);
        Member findMember = memberRepository.findByEmail(email).get();

        ContextMember contextMember = new ContextMember(findMember);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(contextMember, null, contextMember.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void checkRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid);

        if (refreshToken.isPresent()) {
            Member member = memberRepository.findMemberByRefreshToken(refreshToken.get())
                    .orElseThrow(IllegalArgumentException::new);
            String accessToken = jwtService.createAccessToken(member.getEmail());
            jwtService.setAccessTokenInHeader(response, accessToken);
            saveAuthentication(accessToken);
        } else {
            doNotSaveAuthentication();
        }
    }

    private void doNotSaveAuthentication() {
    }
}
