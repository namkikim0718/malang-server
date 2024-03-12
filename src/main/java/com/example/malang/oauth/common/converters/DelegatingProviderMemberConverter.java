package com.example.malang.oauth.common.converters;

import com.example.malang.domain.member.ProviderMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ProviderMemberRequest 를 ProviderMember 로 반환할 수 있도록 해주는 Converter
 */
@Component
public final class DelegatingProviderMemberConverter implements
        ProviderMemberConverter<ProviderMemberRequest , ProviderMember> {

    private List<ProviderMemberConverter<ProviderMemberRequest , ProviderMember>> converters;

    /**
     * 생성자에서 Naver , Kakao , Google 전용 Converter 를 List 에 담고 초기화
     */
    public DelegatingProviderMemberConverter() {
        List<ProviderMemberConverter<ProviderMemberRequest,ProviderMember>> providerMemberConverters =
                    Arrays.asList(new OAuth2NaverProviderMemberConverter(),
                                  new OAuth2KakaoProviderMemberConverter());


        this.converters = Collections.unmodifiableList(new LinkedList<>(providerMemberConverters));
    }


    /**
     * List 에 있는 Converter 를 돌면서 자신에게 Converter 를 찾았을 때 ProviderMember 가 나오고 이를 return 한다.
     */
    @Override
    public ProviderMember convert(ProviderMemberRequest providerMemberRequest) {
        for (ProviderMemberConverter<ProviderMemberRequest, ProviderMember> converter : this.converters) {
            ProviderMember providerMember = converter.convert(providerMemberRequest);
            if (providerMember != null) {
                return providerMember;
            }
        }
        return null;
    }

}
