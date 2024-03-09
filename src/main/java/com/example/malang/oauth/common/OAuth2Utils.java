package com.example.malang.oauth.common;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2Utils {

    public static Attributes getMainTtributes(OAuth2User oAuth2User) {
        return Attributes.builder()
                .mainAttributes(oAuth2User.getAttributes())
                .build();
    }

    public static Attributes getSubAttributes(OAuth2User oAuth2User , String subAttributesKey) {
        /**
         * 바로 Attirubtes 를 꺼낼 수 없는 경우에는 subAttributesKey 를 받아서 그 안에 있는 것을 반환합니다.
         * Naver 에 해당
         */
        Map<String , Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes().get(subAttributesKey);
        return Attributes.builder()
                .subAttributes(subAttributes)
                .build();
    }

    public static Attributes getOtherAttributes(OAuth2User oAuth2User , String subAttributesKey , String otherAttributesKey) {
        /**
         * 응답값이 객체 안에 객체 안에 있는 경우도 있어서 2개의 key 를 받아서 그 안에 있는 것을 반환합니다.
         * Kakao 에 해당
         */
        Map<String , Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes().get(subAttributesKey);
        Map<String , Object> otherAttributes = (Map<String, Object>) subAttributes.get(otherAttributesKey);
        return Attributes.builder()
                .subAttributes(subAttributes)
                .otherAttributes(otherAttributes)
                .build();
    }
}
