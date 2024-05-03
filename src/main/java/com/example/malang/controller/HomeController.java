package com.example.malang.controller;

import com.example.malang.config.BaseResponse;
import com.example.malang.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.malang.dto.MainResponseDto.MainRequiredInfo;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HomeController {

    private final MainService mainService;

    /**
     * 메인 화면 API 입니다.
     */
    @GetMapping("/")
    public ResponseEntity<BaseResponse<MainRequiredInfo>> home(Authentication authentication) {
        return ResponseEntity.ok().body(new BaseResponse<>(mainService.getMainInfo(authentication)));
    }
}
