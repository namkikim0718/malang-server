package com.example.malang.service;

import com.example.malang.domain.ChatParticipation;
import com.example.malang.repository.ChatParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatParticipationService {

    private final ChatParticipationRepository chatParticipationRepository;
    public List<ChatParticipation> findByMemberId(Long memberId) {
        List<ChatParticipation> chatParticipationList = chatParticipationRepository.findByMemberId(memberId);

        return chatParticipationList;
    }

    public ChatParticipation findByMemberIdAndRoomId(Long memberId, Long roomId) {
        ChatParticipation chatParticipation = chatParticipationRepository.findByMemberIdAndChatRoomId(memberId, roomId);

        return chatParticipation;
    }
}
