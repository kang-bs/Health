package com.codingrecipe.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendDTO {
    private Long id;               // friend 테이블 PK
    private Long requesterId;      // 친구 요청 보낸 회원 id
    private Long addresseeId;      // 친구 요청 받은 회원 id
    private String status;         // 'REQUEST', 'ACCEPT', 'REJECT'
    private String requestedAt;    // 요청 시간 (String)
}
