package com.example.malang.dto;

import com.example.malang.domain.member.Member;
import com.example.malang.domain.Place;
import lombok.Getter;


@Getter
public class PostRequest {
    private String title;

    private String content;

    private Member member;

    private Place place;
}
