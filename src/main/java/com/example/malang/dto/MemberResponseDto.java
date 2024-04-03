package com.example.malang.dto;

import com.example.malang.oauth.common.TokenMapping;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDto {

    @Getter @Builder
    public static class LoginAuthenticationMember {
        private Long memberId;
        private TokenMapping tokenMapping;

        public static LoginAuthenticationMember from(TokenMapping tokenMapping , Long memberId) {
            return LoginAuthenticationMember.builder()
                    .memberId(memberId)
                    .tokenMapping(tokenMapping)
                    .build();
        }
    }
}
