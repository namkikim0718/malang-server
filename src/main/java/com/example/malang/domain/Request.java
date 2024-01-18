package com.example.malang.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Request {

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
