package com.example.malang.dto;

import lombok.Data;

@Data
public class SelfLoginResponseDto {

    private Long memberId;

    public SelfLoginResponseDto(Long memberId) {
        this.memberId = memberId;
    }
}
