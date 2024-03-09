package com.example.malang.oauth.common.converters;

import com.example.malang.domain.member.ProviderMember;
import com.example.malang.domain.member.social.KakaoMember;
import com.example.malang.oauth.common.OAuth2Type;
import com.example.malang.oauth.common.OAuth2Utils;

public class OAuth2KakaoProviderMemberConverter implements ProviderMemberConverter<ProviderMemberRequest , ProviderMember> {

    @Override
    public ProviderMember convert(ProviderMemberRequest providerMemberRequest) {

        if (providerMemberRequest.clientRegistration().getRegistrationId().equals(OAuth2Type.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        return new KakaoMember(OAuth2Utils.getOtherAttributes(
                providerMemberRequest.oAuth2User(), "kakao_account","profile"),
                providerMemberRequest.oAuth2User(), providerMemberRequest.clientRegistration());
    }
}
