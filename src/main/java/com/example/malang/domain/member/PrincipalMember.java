package com.example.malang.domain.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public record PrincipalMember(ProviderMember providerMember)implements OAuth2User {
    @Override
    public Map<String, Object> getAttributes() {
        return providerMember.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return providerMember.getAuthorities();
    }

    @Override
    public String getName() {
        return providerMember.getName();
    }
}
