package com.example.malang.oauth.service;

import com.example.malang.repository.MemberRepository;
import com.example.malang.service.MemberService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CustomOAuth2MemberService {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;




}
