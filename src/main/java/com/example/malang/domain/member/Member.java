package com.example.malang.domain.member;

import com.example.malang.domain.ChatParticipation;
import com.example.malang.domain.Post;
import com.example.malang.domain.Request;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    @OneToOne(mappedBy = "member", fetch = LAZY)
    private Post post;

    @OneToMany(mappedBy = "member")
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatParticipation> chatParticipationList = new ArrayList<>();

}
