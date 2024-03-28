package com.example.malang.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String name;

    private String lat;

    private String lng;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "place")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Place(String name, String lat, String lng, Address address) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }
}
