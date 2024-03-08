package com.example.malang.service;

import com.example.malang.domain.ChatRoom;
import com.example.malang.domain.Post;
import com.example.malang.domain.Request;
import com.example.malang.repository.ChatRoomRepository;
import com.example.malang.repository.PostRepository;
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

    // 채팅방 생성
    @Transactional
    public Long createChatRoom(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException());

        ChatRoom chatRoom = ChatRoom.createChatRoom(request);
        chatRoomRepository.save(chatRoom);
        log.info("[createChatRoom] 채팅방 생성 성공");
        return chatRoom.getId();
    }
}
