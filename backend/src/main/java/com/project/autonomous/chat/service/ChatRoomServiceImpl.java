package com.project.autonomous.chat.service;

import com.project.autonomous.chat.dto.request.ChatRoomMessageReq;
import com.project.autonomous.chat.dto.response.ChatRoomListRes;
import com.project.autonomous.chat.entity.ChatMessage;
import com.project.autonomous.chat.entity.ChatRoom;
import com.project.autonomous.chat.entity.UserChatRoom;
import com.project.autonomous.chat.repository.UserChatRoomRepository;
import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService{

//    private final ChatRoomRepository chatRoomRepository;
    private final UserChatRoomRepository userChatRoomRepository;
//    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    //채팅방 목록 조회
    @Override
    public List<ChatRoomListRes> getChatRoomList(Long userId){

        List<ChatRoomListRes> chatRoomListResList = new ArrayList<>();

        User user = findMember(userId);
        List<UserChatRoom> userChatRoomList = userChatRoomRepository.findAllByUserChatRoomIdUser(user);

        for(UserChatRoom ucr : userChatRoomList){
            ChatRoomListRes chatRoomListRes = new ChatRoomListRes();

            ChatRoom chatRoom = ucr.getUserChatRoomId().getChatRoom();

            String partnerName = chatRoom.getPub().equals(user)? chatRoom.getSub().getName() : chatRoom.getPub().getName();
            String partnerEmail = chatRoom.getPub().equals(user)? chatRoom.getSub().getEmail() : chatRoom.getPub().getEmail();

            chatRoomListRes.setRoomId(chatRoom.getId());
            chatRoomListRes.setPartnerName(partnerName);
            chatRoomListRes.setPartnerEmail(partnerEmail);
            chatRoomListRes.setLatestMessageDate(chatRoom.getLatestMessageTime());

            chatRoomListResList.add(chatRoomListRes);
        }

        return chatRoomListResList;
    }

    @Override
    public List<ChatMessage> getMessage(ChatRoomMessageReq chatRoomMessageReq) {

        //user체크
        User pub =  findMember(chatRoomMessageReq.getPub());
        User sub =  findMember(chatRoomMessageReq.getSub());

//        UserChatRoomId chatRoomId = new UserChatRoomId(pub,sub);
        //채팅방 유무 체크
//        if(chatRoomRepository.existsByChatRoomId(chatRoomId)){
//            //채팅방 있다면 Message return
//
//            return null;
//
//        }else{ //없다면 생성
//            return create(pub,sub);
//        }

        return null;
    }

    public User findMember(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

//    public List<ChatMessage> create(User pub, User sub){
//
//        ChatRoom chatRoom = new ChatRoom();
//
////        chatRoom.setChatRoomId(new UserChatRoomId(pub,sub));
//        chatRoom.setPubState(true);
//        chatRoom.setSubState(true);
//        chatRoom.setLatestMessageTime(LocalDateTime.now());
//
//        chatRoomRepository.save(chatRoom);
//
//        return new ArrayList<ChatMessage>();
//    }
}
