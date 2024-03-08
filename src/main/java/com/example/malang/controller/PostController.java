package com.example.malang.controller;


import com.example.malang.domain.member.Member;
import com.example.malang.domain.Post;
import com.example.malang.dto.PostDetailResponseDTO;
import com.example.malang.dto.PostRequest;
import com.example.malang.dto.PostResponseDTO;
import com.example.malang.service.MemberService;
import com.example.malang.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;


    @PostMapping(value = "/members/{memberId}/post", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Long createPost(@PathVariable Long memberId, @RequestPart PostRequest postRequest, @RequestPart MultipartFile imageFile) throws IOException {
        //Member member = memberService.findById(memberId);

        return postService.createPost(postRequest.getTitle(), postRequest.getContent(), postRequest.getMember(), postRequest.getPlace(), imageFile);
    }


    @GetMapping("/posts")
    public List<PostResponseDTO> findAll() {
        List<Post> postList = postService.findAll();

        return postList.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/{postId}")
    public PostDetailResponseDTO findById(@PathVariable Long postId) {
        Post post = postService.findById(postId)
                .orElseThrow(
                        NullPointerException::new
                );

        return new PostDetailResponseDTO(post);
    }
}
