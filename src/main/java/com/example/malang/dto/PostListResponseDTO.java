package com.example.malang.dto;

import com.example.malang.domain.Post;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostListResponseDTO {
    private Long id;

    private String title;

    private String placeName;

    private String uploadFileName;

    private String storeFileName;

    public PostListResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.placeName = post.getPlace().getName();
        this.uploadFileName = post.getUploadFileName();
        this.storeFileName = post.getStoreFileName();
    }
}
