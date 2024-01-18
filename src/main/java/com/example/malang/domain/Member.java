package com.example.malang.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String loginId;

    private String password;

    private String email;

    @OneToOne(mappedBy = "member")
    private Post post;

    @OneToMany(mappedBy = "member")
    private List<Request> requests = new ArrayList<>();
}
