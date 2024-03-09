package com.example.malang.domain;

import com.example.malang.domain.member.Member;
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
    // 요청보낸 Member
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    // 요청 받는 Member -> Post.Member
    private Post post;

    @OneToOne(mappedBy = "request", fetch = LAZY)
    private ChatRoom chatRoom;
}
