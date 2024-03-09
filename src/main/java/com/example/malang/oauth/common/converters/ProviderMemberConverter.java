package com.example.malang.oauth.common.converters;

/**
 * RegistraionId 가 Naver 면 NaverMember 를 , Kakao 면 KakaoMember 를 반환할 수 있는 로직
 * @param <T> : 입력 타입
 * @param <R> : 반환 타입
 */
public interface ProviderMemberConverter<T,R>{
    R converter(T t);
}
