package com.example.malang.dto;

import lombok.Data;

@Data
public class SelfLoginResponseDto {

    private Long memberId;
    private String name;
    private String email;

    public SelfLoginResponseDto(Long memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }
}
