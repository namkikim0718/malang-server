package com.example.malang.dto;

import com.example.malang.domain.Place;
import com.example.malang.domain.Post;
import com.example.malang.domain.member.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponseDTO {
    private Long id;

    private String title;

    private Long placeId;

    private String content;

    private String memberName;

    private int age;

    private int male_members;

    private int female_members;

    private String uploadFileName;

    private String storeFileName;

    public PostDetailResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.placeId = post.getPlace().getId();
        this.content = post.getContent();
        this.memberName = post.getMember().getName();
        this.age = post.getAge();
        this.male_members = post.getMale_members();
        this.female_members = post.getFemale_members();
        this.uploadFileName = post.getUploadFileName();
        this.storeFileName = post.getStoreFileName();
    }
}
