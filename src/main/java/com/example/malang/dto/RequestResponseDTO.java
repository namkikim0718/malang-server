package com.example.malang.dto;

import com.example.malang.domain.ChatRoom;
import com.example.malang.domain.Post;
import com.example.malang.domain.Request;
import com.example.malang.domain.RequestStatus;
import com.example.malang.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
public class RequestResponseDTO {

    private Long id;

    private String message;

    private RequestStatus status;

    // 요청보낸 Member
    private Long memberId;

    // 요청 받는 Member -> Post.Member
    private Long postId;

    private Long chatRoomId;


    public RequestResponseDTO(Request request) {
        this.id = request.getId();
        this.message = request.getMessage();
        this.status = request.getStatus();
        this.memberId = request.getMember().getId();
        this.postId = request.getPost().getId();
        if (status.equals(RequestStatus.WAIT)) {
            this.chatRoomId = null;
        } else {
            this.chatRoomId = request.getChatRoom().getId();
        }

    }
}
