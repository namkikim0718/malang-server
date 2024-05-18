package com.example.malang.dto;

import lombok.Data;

@Data
public class SelfJoinResponseDto {
    private Long memberId;
    private String email;
    private String name;

    public SelfJoinResponseDto(Long memberId, String email, String name) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
    }
}
