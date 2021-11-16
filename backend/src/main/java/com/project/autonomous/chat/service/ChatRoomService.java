package com.project.autonomous.chat.service;

import com.project.autonomous.chat.dto.request.ChatRoomMessageReq;
import com.project.autonomous.chat.dto.response.ChatRoomListRes;
import com.project.autonomous.chat.entity.ChatMessage;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomListRes> getChatRoomList(Long userId);
    List<ChatMessage> getMessage(ChatRoomMessageReq chatRoomMessageReq);
}
