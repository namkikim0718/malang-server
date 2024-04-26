package com.example.malang.dto;

import com.example.malang.jwt.TokenMapping;
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

    @Getter @Builder
    public static class MyPage {
        private Long memberId;
        private String memberName;
        private String email;

        public static MyPage to(Long memberId , String memberName , String email) {
            return MyPage.builder()
                    .memberId(memberId)
                    .email(email)
                    .memberName(memberName)
                    .build();
        }


    }
}
