package com.example.malang.controller;

import com.example.malang.config.BaseResponse;
import com.example.malang.dto.*;
import com.example.malang.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class MemberController {

    private final MemberService memberService;


    // 자체 회원가입
    @PostMapping("/members/self-join")
    public ResponseEntity<BaseResponse<SelfJoinResponseDto>> selfJoin(@RequestBody SelfJoinRequestDto selfJoinRequestDto) {
        return ResponseEntity.ok().body(new BaseResponse<>(memberService.selfJoin(selfJoinRequestDto)));
    }

    // 자체 로그인
    @PostMapping("/members/self-login")
    public ResponseEntity<BaseResponse<SelfLoginResponseDto>> selfLogin(@RequestBody SelfLoginRequestDto selfLoginRequestDto) {
        return ResponseEntity.ok().body(new BaseResponse<>(memberService.selfLogin(selfLoginRequestDto)));
    }

    // Oauth 인증 방식
    @PostMapping("/members/oauth-join")
    public ResponseEntity<BaseResponse<SelfJoinResponseDto>> join(@RequestBody JoinDTO joinDTO) {
        log.info("[email] = {}", joinDTO.getEmail());
        return ResponseEntity.ok().body(new BaseResponse<>(memberService.join(joinDTO)));
    }

//    /**
//     * MyPage API
//     */
//    @GetMapping("/members/{member-id}/my-page")
//    public ResponseEntity<BaseResponse<MyPage>> getMyPage(@PathVariable("member-id") Long memberId) {
//        return ResponseEntity.ok().body(new BaseResponse<>(memberService.getMyPage(memberId)));
//    }
//
//    /**
//     * MyPage -> 내가 작성한 게시글
//     */
//    // TODO 여기서부터 시작!
//    @GetMapping("/members/{member-id}/my-page/my-post")
//    public ResponseEntity<BaseResponse<MyPage>> getMyPageForMyPost(@PathVariable("member-id") Long memberId) {
//        return ResponseEntity.ok().body(new BaseResponse<>(memberService.getMyPage(memberId)));
//    }
}
