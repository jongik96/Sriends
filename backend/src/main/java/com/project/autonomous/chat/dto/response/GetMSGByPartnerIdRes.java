package com.project.autonomous.chat.dto.response;

import com.project.autonomous.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetMSGByPartnerIdRes {
    List<ChatMessage> chatMessageList;
    Long roomId;
}
