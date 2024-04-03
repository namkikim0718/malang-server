package com.example.malang.controller;

import com.example.malang.dto.MemberRequestDto;
import com.example.malang.dto.MemberResponseDto;
import com.example.malang.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.malang.dto.MemberRequestDto.*;
import static com.example.malang.dto.MemberResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/sign-up")
    public ResponseEntity<LoginAuthenticationMember> signUp(@RequestBody OauthLoginMember oAuthLoginMember) {
        return ResponseEntity.ok().body(memberService.signUp(oAuthLoginMember));
    }


}
