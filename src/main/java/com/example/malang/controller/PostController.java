package com.example.malang.controller;

import com.example.malang.config.BaseResponse;

import com.example.malang.dto.PostRequestDto;
import com.example.malang.dto.PostResponseDto;
import com.example.malang.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class PostController {

    private final PostService postService;


    // 게시글 생성
    @PostMapping(value = "/members/{memberId}/post", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseResponse<Long>> createPost(@PathVariable("memberId") Long memberId, @RequestPart("postRequest") PostRequestDto.PostRequest postRequest, @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
            return ResponseEntity.ok().body(new BaseResponse<>(postService.createPost(memberId, postRequest, imageFile)));
    }


    // 게시글 리스트 조회
    @GetMapping("/posts")
    public ResponseEntity<BaseResponse<List<PostResponseDto.PostListResponseDTO>>> findAll() {
        return ResponseEntity.ok().body(new BaseResponse<>(postService.findAllPost()));
    }


    // 게시글 단건 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<BaseResponse<PostResponseDto.PostDetailResponseDTO>> findById(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok().body(new BaseResponse<>(postService.findPostById(postId)));
    }

}
