package com.example.malang.controller;

import com.amazonaws.Response;
import com.example.malang.config.BaseResponse;
import com.example.malang.dto.MemberRequestDto;
import com.example.malang.dto.MemberResponseDto;
import com.example.malang.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.malang.dto.MemberRequestDto.*;
import static com.example.malang.dto.MemberResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/sign-up")
    public ResponseEntity<BaseResponse<LoginAuthenticationMember>> signUp(@RequestBody OauthLoginMember oAuthLoginMember) {
        return ResponseEntity.ok().body(new BaseResponse<>(memberService.signUp(oAuthLoginMember)));
    }

    /**
     * MyPage API
     */
    @GetMapping("/members/{member-id}/my-page")
    public ResponseEntity<BaseResponse<MyPage>> getMyPage(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().body(new BaseResponse<>(memberService.getMyPage(memberId)));
    }



}
