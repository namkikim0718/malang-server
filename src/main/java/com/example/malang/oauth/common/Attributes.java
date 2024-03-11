package com.example.malang.oauth.common;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * 해당 클래스는 Naver , Kakao , Google 마다 응답값이 다르기 때문에
 * 각 응답값을 따로 가져온 후 하나의 응답값으로 만들기 위한 클래스입니다.
 */
@Data
@Builder
public class Attributes {
    // google
    private Map<String , Object> mainAttributes;
    // naver
    private Map<String , Object> subAttributes;
    // kakao
    private Map<String , Object> otherAttributes;
}
