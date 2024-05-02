package com.example.malang.repository;


import com.example.malang.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    Optional<Member> findMemberByRefreshToken(String refreshToken);
    Optional<Member> findByEmail(String email);
}
