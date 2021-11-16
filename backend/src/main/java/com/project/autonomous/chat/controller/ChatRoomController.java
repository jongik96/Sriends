package com.project.autonomous.chat.controller;

import com.project.autonomous.chat.dto.request.ChatRoomMessageReq;
import com.project.autonomous.chat.dto.response.ChatRoomListRes;
import com.project.autonomous.chat.entity.ChatMessage;
import com.project.autonomous.chat.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

   private final ChatRoomService chatRoomservice;

//    @Autowired
//    ChatRoomService chatRoomservice;

    //채팅방 전체 조회
    @GetMapping("/list/{userId}")
    @Operation(summary = "본인 채팅 조회", description = "본인 채팅목록 조회")
    public ResponseEntity<List<ChatRoomListRes>> getTeamList(@PathVariable Long userId){

        List<ChatRoomListRes> list = chatRoomservice.getChatRoomList(userId);
        return ResponseEntity.ok(list);
    }

    //채팅방 조회
    @GetMapping("/room")
    public ResponseEntity<List<ChatMessage>> getMessage(@RequestBody ChatRoomMessageReq chatRoomMessageReq){
        return ResponseEntity.ok(chatRoomservice.getMessage(chatRoomMessageReq));
    }


}
