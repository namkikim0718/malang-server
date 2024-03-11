package com.example.malang.dto;


import com.example.malang.domain.member.Member;
import com.example.malang.domain.Place;
import com.example.malang.domain.Post;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponseDTO {
    private Long id;
    private String title;

    private String content;

    private Member member;

    private Place place;

    private String uploadFileName;

    private String storeFileName;

    public PostDetailResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.member = post.getMember();
        this.place = post.getPlace();
        this.uploadFileName = post.getUploadFileName();
        this.storeFileName = post.getStoreFileName();
    }
}
