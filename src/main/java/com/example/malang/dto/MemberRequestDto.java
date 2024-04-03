package com.example.malang.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDto {

    @Getter @Builder
    public static class OAuthLoginMember {
        /**
         * 카카오 회원가입 + 로그인 후 넘어오는 값을 여기에 추가하며 됩니다.
         */
        private String email;
    }


}
