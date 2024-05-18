package com.example.malang.dto;

import lombok.Data;

@Data
public class SelfJoinResponseDto {
    private Long memberId;
    private String email;

    public SelfJoinResponseDto(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}
