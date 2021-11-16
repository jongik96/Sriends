package com.project.autonomous.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomListRes {
    Long roomId;
    String partnerName;
    String partnerEmail;
    LocalDateTime latestMessageDate;
}
