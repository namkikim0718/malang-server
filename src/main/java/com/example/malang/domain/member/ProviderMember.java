package com.example.malang.domain.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

public interface ProviderMember {
    String getId();
    String getName();
    String getEmail();
    String getProvider();
    List<? extends GrantedAuthority> getAuthorities();
    // Provider 에게 받는 값
    Map<String, Object> getAttributes();
    OAuth2User getOAuth2User();
}
