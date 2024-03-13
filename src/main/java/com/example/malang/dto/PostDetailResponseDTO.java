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

    private String content;

    private String memberName;

    private String uploadFileName;

    private String storeFileName;

    public PostDetailResponseDTO(Long id, String title, String content, String memberName, String uploadFileName, String storeFileName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberName = memberName;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
