package com.example.malang.dto;

import com.example.malang.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.example.malang.dto.PostResponseDto.*;

public class MainResponseDto {

    @Getter @Builder
    public static class MainRequiredInfo {
        private String memberName;
        private List<PostListResponseDTO> postAll;

        public static MainRequiredInfo toDto(String name, List<PostListResponseDTO> posts) {
            return MainRequiredInfo.builder()
                    .memberName(name)
                    .postAll(posts)
                    .build();
        }
    }
}
