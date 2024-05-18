package com.example.malang.service;

import com.example.malang.domain.member.Member;
import com.example.malang.dto.*;
import com.example.malang.exception.BaseException;
import com.example.malang.exception.ErrorCode;
import com.example.malang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public SelfJoinResponseDto join(JoinDTO joinDTO) {

        // db에 이미 동일한 username을 가진 회원이 존재하는지?
        if (memberRepository.existsByUsername(joinDTO.getEmail())) {
            log.info("[join] 이미 회원가입한 회원");
        }

        String password = joinDTO.getName() + " " + joinDTO.getEmail();
        log.info("[join] 회원가입 성공");
        Member savedMember = memberRepository.save(Member.createMember(joinDTO.getName(), joinDTO.getEmail(), bCryptPasswordEncoder.encode(password)));
        return new SelfJoinResponseDto(savedMember.getId() , savedMember.getUsername());
    }

    @Transactional
    public SelfJoinResponseDto selfJoin(SelfJoinRequestDto selfJoinRequestDto) {
        // db에 이미 동일한 username을 가진 회원이 존재하는지?
        if (memberRepository.existsByUsername(selfJoinRequestDto.getEmail())) {
            log.info("[join] 이미 회원가입한 회원");
        }

        Member savedMember = memberRepository
                .save(Member.createMember(selfJoinRequestDto.getName(), selfJoinRequestDto.getEmail(), bCryptPasswordEncoder.encode(selfJoinRequestDto.getPassword())));
        return new SelfJoinResponseDto(savedMember.getId() , savedMember.getUsername());
    }

    public SelfLoginResponseDto selfLogin(SelfLoginRequestDto selfLoginRequestDto) {
        Member member = memberRepository.findByUsername(selfLoginRequestDto.getEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_MEMBER));

        log.info("selfLoginRequestDtoPassword = {} " , selfLoginRequestDto.getPassword());
        log.info("memberPassword = {} " , member.getPassword());

        if (!bCryptPasswordEncoder.matches(selfLoginRequestDto.getPassword(), member.getPassword())) {
            throw new BaseException(ErrorCode.NOT_MATCH_PASSWORD_MEMBER);
        }
        return new SelfLoginResponseDto(member.getId(),member.getName(),member.getUsername());
    }
}
