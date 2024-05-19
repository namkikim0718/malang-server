package com.example.malang.service;

import com.example.malang.domain.ChatParticipation;
import com.example.malang.domain.ChatRoom;
import com.example.malang.domain.Request;
import com.example.malang.domain.member.Member;
import com.example.malang.repository.ChatParticipationRepository;
import com.example.malang.repository.ChatRoomRepository;
import com.example.malang.repository.MemberRepository;
import com.example.malang.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final RequestRepository requestRepository;

    private final MemberRepository memberRepository;

    private final ChatParticipationRepository chatParticipationRepository;

    // 채팅방 생성
    @Transactional
    public Long createChatRoom(Long member1Id, Long member2Id) {
        Member member1 = memberRepository.findById(member1Id)
                .orElseThrow(() -> new RuntimeException());

        Member member2 = memberRepository.findById(member2Id)
                .orElseThrow(() -> new RuntimeException());


        ChatRoom chatRoom = new ChatRoom();
        chatRoomRepository.save(chatRoom);
        log.info("[createChatRoom] 채팅방 생성 성공");

        ChatParticipation chatParticipation1 = ChatParticipation.createChatParticipation(member1, chatRoom);
        ChatParticipation chatParticipation2 = ChatParticipation.createChatParticipation(member2, chatRoom);

        chatParticipationRepository.save(chatParticipation1);
        chatParticipationRepository.save(chatParticipation2);
        log.info("[createChatParticipation] 채팅 참여 생성 성공");

        return chatRoom.getId();
    }

    @Transactional
    public void deleteChatRoom(Long roomId) {
        chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException());

        chatRoomRepository.deleteById(roomId);
    }
}
