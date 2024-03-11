package com.example.malang.repository;


import com.example.malang.domain.ChatParticipation;
import com.example.malang.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
