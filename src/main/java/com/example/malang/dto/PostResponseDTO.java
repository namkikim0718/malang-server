package com.example.malang.dto;

import com.example.malang.domain.Place;
import com.example.malang.domain.Post;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDTO {
    private Long id;

    private String title;

    private Place place;

    private String uploadFileName;

    private String storeFileName;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.place = post.getPlace();
        this.uploadFileName = post.getUploadFileName();
        this.storeFileName = post.getStoreFileName();
    }
}
