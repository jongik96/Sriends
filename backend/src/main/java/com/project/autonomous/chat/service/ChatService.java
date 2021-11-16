package com.project.autonomous.chat.service;

import com.project.autonomous.chat.dto.request.ChatMessageReq;
import com.project.autonomous.chat.dto.response.ChatRoomListRes;
import com.project.autonomous.chat.entity.ChatMessage;

import java.util.List;

public interface ChatService {

    List<ChatRoomListRes> getChatRoomList();
    List<ChatMessage> getMessageByPartnerId(Long partnerId);
    List<ChatMessage> getMessageByRoomId(Long roomId);
    void saveMessage(ChatMessageReq messageReq);
}
