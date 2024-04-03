package com.example.malang.dto;

import com.example.malang.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MainResponseDto {

    @Getter @Builder
    public static class MainRequiredInfo {
        private String memberName;
        private List<PostResponseDto.PostListResponseDTO> postAll;

        public static MainRequiredInfo toDto(String name, List<PostResponseDto.PostListResponseDTO> posts) {
            return MainRequiredInfo.builder()
                    .memberName(name)
                    .postAll(posts)
                    .build();
        }
    }
}
