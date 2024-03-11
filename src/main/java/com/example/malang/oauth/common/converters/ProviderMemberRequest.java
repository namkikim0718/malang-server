package com.example.malang.oauth.common.converters;

import lombok.Data;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record ProviderMemberRequest (ClientRegistration clientRegistration , OAuth2User oAuth2User) {

    public ProviderMemberRequest(ClientRegistration clientRegistration , OAuth2User oAuth2User) {
        this.clientRegistration = clientRegistration;
        this.oAuth2User = oAuth2User;
    }
}
