package com.example.malang.domain.member.social;

import com.example.malang.domain.member.OAuth2ProviderMember;
import com.example.malang.oauth.common.Attributes;
import lombok.Getter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.List;
import java.util.Map;

/**
 * Naver , Kakao , Google 다 다르게 나누는 이유는
 * 각 인가서버에서 넘기는 형태가 다르기 때문에 나눠서 설계
 * 최대한 공통적으로 가져올 수 있는 부분은 OAuth2ProviderMember 에서 구현
 */

@Getter
public class KakaoMember extends OAuth2ProviderMember {

    private final Map<String,Object> subAttributes;

    public KakaoMember(Attributes attributes, OAuth2User oAuth2User , ClientRegistration clientRegistration) {
        super(attributes.getOtherAttributes() , oAuth2User , clientRegistration);
        this.subAttributes = attributes.getOtherAttributes();
    }


    @Override
    public String getId() {
        return (String) getAttributes().get("id");
    }

    @Override
    public String getName() {
        return (String) getAttributes().get("nickname");
    }
}
