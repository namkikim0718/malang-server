package com.example.malang.oauth.common.converters;

import com.example.malang.domain.member.ProviderMember;
import com.example.malang.domain.member.social.NaverMember;
import com.example.malang.oauth.common.OAuth2Type;
import com.example.malang.oauth.common.OAuth2Utils;

public class OAuth2NaverProviderMemberConverter implements ProviderMemberConverter<ProviderMemberRequest , ProviderMember> {
    @Override
    public ProviderMember convert(ProviderMemberRequest providerMemberRequest) {

        /**
         * Naver 로부터 온 Rquest 가 아니라면 null
         */
        if (!providerMemberRequest.clientRegistration().getRegistrationId()
                .equals(OAuth2Type.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverMember(OAuth2Utils.getSubAttributes(
                providerMemberRequest.oAuth2User(), "response"),
                providerMemberRequest.oAuth2User(),providerMemberRequest.clientRegistration());
    }
}
