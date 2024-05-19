package com.example.malang.controller;

import com.example.malang.domain.ChatRoom;
import com.example.malang.dto.ChatRoomRequest;
import com.example.malang.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/members/{memberId}/rooms")
    public Long createChatRoom(@PathVariable("memberId") Long memberId, @RequestBody ChatRoomRequest chatRoomRequest) {
        log.info("member1Id : {}", memberId);
        log.info("member2Id: {}", chatRoomRequest.getId());
        Long createChatRoom = chatRoomService.createChatRoom(memberId, chatRoomRequest.getId());
        log.info("createChatRoom : {}", createChatRoom);
        return createChatRoom;
    }

    @DeleteMapping("/rooms/{roomId}")
    public void deleteChatRoom(@PathVariable Long roomId) {
        log.info("[roomId] = {}", roomId);
        chatRoomService.deleteChatRoom(roomId);
    }
}
