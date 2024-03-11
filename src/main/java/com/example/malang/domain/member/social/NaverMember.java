package com.example.malang.domain.member.social;

import com.example.malang.domain.member.OAuth2ProviderMember;
import com.example.malang.oauth.common.Attributes;
import lombok.Getter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.List;
import java.util.Map;

@Getter
public class NaverMember extends OAuth2ProviderMember {

    private String registrationId;

    private List<OAuth2UserAuthority> authorities;

    public NaverMember(Attributes attributes, OAuth2User oAuth2User , ClientRegistration clientRegistration) {
        super(attributes.getSubAttributes(), oAuth2User , clientRegistration);
    }

    @Override
    public String getId() {
        return (String) getAttributes().get("id");
    }

    @Override
    public String getName() {
        return (String) getAttributes().get("email");
    }
}
