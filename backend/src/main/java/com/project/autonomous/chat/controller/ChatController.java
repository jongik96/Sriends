package com.project.autonomous.chat.controller;

import com.project.autonomous.chat.dto.request.ChatMessageReq;
import com.project.autonomous.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/chat")
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageReq message) {
        // if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
        // message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        // }
        // System.out.println(message.toString());

        chatService.saveMessage(message);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
