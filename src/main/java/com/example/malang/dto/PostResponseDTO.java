package com.example.malang.dto;

import com.example.malang.domain.Place;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDTO {
    private String title;
    private Place place;
    private String uploadFileName;
    private String storeFileName;
}
