package com.example.malang.domain.member.social;

import com.example.malang.domain.member.OAuth2ProviderMember;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.List;
import java.util.Map;

public class NaverMember extends OAuth2ProviderMember {

    private NaverMember(OAuth2User oAuth2User , ClientRegistration clientRegistration) {
        super((Map<String, Object>) oAuth2User.getAttributes().get("response") , oAuth2User , clientRegistration);
    }

    private List<OAuth2UserAuthority> authorities;
    @Override
    public String getId() {
        return (String) getAttributes().get("id");
    }

    @Override
    public String getName() {
        return (String) getAttributes().get("email");
    }
}
