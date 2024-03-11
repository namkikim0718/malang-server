package com.example.malang.oauth.service;

import com.example.malang.domain.member.Member;
import com.example.malang.domain.member.PrincipalMember;
import com.example.malang.domain.member.ProviderMember;
import com.example.malang.oauth.common.converters.ProviderMemberConverter;
import com.example.malang.oauth.common.converters.ProviderMemberRequest;
import com.example.malang.repository.MemberRepository;
import com.example.malang.service.MemberService;
import jakarta.persistence.spi.ProviderUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CustomOAuth2MemberService implements OAuth2UserService<OAuth2UserRequest , OAuth2User> {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProviderMemberConverter<ProviderMemberRequest , ProviderMember> providerMemberConverter;

    public void signIn(ProviderMember providerMember , OAuth2UserRequest oAuth2UserRequest) {

        Member member = memberRepository.findByName(providerMember.getName());

        if (member==null) {
            ClientRegistration clientRegistration = oAuth2UserRequest.getClientRegistration();
            memberService.signIn(clientRegistration.getRegistrationId(),providerMember);
        }
        /**
         * 여기에 이미 가입된 회원이 있는 경우 예외 처리를 만들어야합니다.
         * 만들 예정
         */
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest , OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        /**
         * loadUser() 에서 사용자의 소셜 로그인을 성공한 객체를 가져옵니다.
         */
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        ProviderMemberRequest providerMemberRequest = new ProviderMemberRequest(clientRegistration,oAuth2User);
        ProviderMember providerMember = providerMember(providerMemberRequest);

        /**
         * 최동적으로 소셜 로그인의 객체인 ProviderMember 를 회원 가입 하는 로직의 파라미터로 전달합니다.
         */
        signIn(providerMember,userRequest);
        return new PrincipalMember(providerMember);
    }

    /**
     * 해당 method 를 통해 바로 해당 소셜 로그인 객체로 만들어서 가져옵니다.
     */
    public ProviderMember providerMember(ProviderMemberRequest providerMemberRequest) {
        return providerMemberConverter.convert(providerMemberRequest);
    }
}
