package com.example.malang.domain.member;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provider 끼리 가져오는 값이나 방식이 다른 Method 들은 Member 클래스에서 구현
 * 그 외 공통 되는 Method 는 추상 클래스에서 구현
 */
@AllArgsConstructor
public abstract class OAuth2ProviderMember implements ProviderMember{

    private Map<String , Object> attributes;
    private OAuth2User oAuth2User;
    private ClientRegistration clientRegistration;

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getProvider() {
        return clientRegistration.getRegistrationId();
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities().stream().map(authority ->
                new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public OAuth2User getOAuth2User() {
        return oAuth2User;
    }
}
