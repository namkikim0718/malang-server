package com.example.malang.dto;

import com.example.malang.domain.Post;
import lombok.Builder;
import lombok.Getter;

public class PostResponseDto {

    @Getter @Builder
    public static class PostDetailResponseDTO {

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

    @Builder @Getter
    public static class PostListResponseDTO {

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
}
