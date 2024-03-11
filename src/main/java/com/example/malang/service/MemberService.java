package com.example.malang.service;

import com.example.malang.domain.member.Member;
import com.example.malang.domain.member.ProviderMember;
import com.example.malang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signIn(String registrationId , ProviderMember providerMember) {
        Member member = Member.builder()
                .name(providerMember.getName())
                .email(providerMember.getEmail())
                .build();
        memberRepository.save(member);
    }

    public Optional<Member> findById(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
