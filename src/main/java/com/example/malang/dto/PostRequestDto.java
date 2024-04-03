package com.example.malang.dto;

import com.example.malang.domain.Address;
import lombok.Builder;
import lombok.Getter;

public class PostRequestDto {

    /**
     * 무슨 Dto 인지 몰라서 일단 PostRequest 설정
     */
    @Getter @Builder
    public static class PostRequest{
        private String title;

        private String placeName;

        private String lat;

        private String lng;

        private Address address;

        private int age;

        private int male_members;

        private int female_members;

        private String content;
    }
}
