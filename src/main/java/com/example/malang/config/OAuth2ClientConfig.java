package com.example.malang.config;

import com.example.malang.oauth.common.CustomAuthorityMapper;
import com.example.malang.oauth.filter.JwtAuthorizationFilter;
import com.example.malang.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.example.malang.oauth.service.CustomOAuth2MemberService;
import com.example.malang.oauth.service.JwtService;
import com.example.malang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.client.web.method.annotation.OAuth2AuthorizedClientArgumentResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor
@Configuration
public class OAuth2ClientConfig {

    private final CustomOAuth2MemberService customOAuth2MemberService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());
//                .requestMatchers(
//                        new AntPathRequestMatcher("/"),
//                        new AntPathRequestMatcher("/login/**"),
//                        new AntPathRequestMatcher("/jiwon")
//                )
                /**
                 * MALANG_ROLE 의 권한을 가진 유저만 호출 가능합니다.
                 * 소셜 로그인 성공하면 MALANG_ROLE 권한을 주도록 했습니다.
                 */
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).hasAuthority("MALANG_ROLE")

        http.sessionManagement(sessionManager -> sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(userInfoEndpointConfig ->
                userInfoEndpointConfig.userService(customOAuth2MemberService)));
        http.formLogin(formLogin ->
                formLogin.loginPage("/login").loginProcessingUrl("/loginProc").defaultSuccessUrl("/").permitAll());
        http.oauth2Login(oauth2Login -> oauth2Login.successHandler(oAuth2AuthenticationSuccessHandler));

        http
                .addFilterBefore(jwtAuthorizationFilter , OAuth2LoginAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }

}
