package com.example.malang.dto;


import com.example.malang.domain.Address;
import com.example.malang.domain.Place;
import com.example.malang.domain.member.Member;
import jakarta.persistence.Embedded;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class PostRequest {
    private String title;

    private String placeName;

    private String lat;

    private String lng;

    private Address address;

    private int age;

    private int male_members;

    private int female_members;

    private String content;
    /*
    public PostRequest(String title, String placeName, String lat, String lng, Address address, int age, int male_members, int female_members, String content) {
        this.title = title;
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.age = age;
        this.male_members = male_members;
        this.female_members = female_members;
        this.content = content;
    }

     */
}
