package com.example.malang.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(mappedBy = "request", fetch = LAZY)
    private ChatRoom chatRoom;
}
