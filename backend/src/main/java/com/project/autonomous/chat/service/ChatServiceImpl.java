package com.project.autonomous.chat.service;

import com.project.autonomous.chat.dto.request.ChatMessageReq;
import com.project.autonomous.chat.dto.response.ChatRoomListRes;
import com.project.autonomous.chat.dto.response.GetMSGByPartnerIdRes;
import com.project.autonomous.chat.entity.ChatMessage;
import com.project.autonomous.chat.entity.ChatRoom;
import com.project.autonomous.chat.entity.UserChatRoom;
import com.project.autonomous.chat.repository.ChatRepository;
import com.project.autonomous.chat.repository.ChatRoomRepository;
import com.project.autonomous.chat.repository.UserChatRoomRepository;
import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    //채팅방 목록 조회
    @Override
    public List<ChatRoomListRes> getChatRoomList(){

        List<ChatRoomListRes> chatRoomListResList = new ArrayList<>();

        User user = findMember(SecurityUtil.getCurrentMemberId());
        List<UserChatRoom> userChatRoomList = userChatRoomRepository.findAllByUserChatRoomIdUser(user);

        for(UserChatRoom ucr : userChatRoomList){
            ChatRoomListRes chatRoomListRes = new ChatRoomListRes();

            ChatRoom chatRoom = ucr.getUserChatRoomId().getChatRoom();

            String partnerName = "";
            String partnerEmail = "";
            String partnerPicture = "";

            if(chatRoom.getPub().equals(user)){
                partnerName = chatRoom.getSub().getName();
                partnerEmail = chatRoom.getSub().getEmail();
                partnerPicture = chatRoom.getSub().getPicture().getImageUrl();
            }else{
                partnerName = chatRoom.getPub().getName();
                partnerEmail = chatRoom.getPub().getEmail();
                partnerPicture = chatRoom.getPub().getPicture().getImageUrl();
            }

            chatRoomListRes.setRoomId(chatRoom.getId());
            chatRoomListRes.setPartnerName(partnerName);
            chatRoomListRes.setPartnerEmail(partnerEmail);
            chatRoomListRes.setPartnerPicture(partnerPicture);

            chatRoomListRes.setLatestMessageDate(chatRoom.getLatestMessageTime());

            chatRoomListResList.add(chatRoomListRes);
        }

        return chatRoomListResList;
    }


    //채팅방 접속 - 채팅목록에서
    @Override
    @Transactional
    public GetMSGByPartnerIdRes getMessageByPartnerId(Long partnerId) {

        //user체크

        User user = findMember(SecurityUtil.getCurrentMemberId());
        User partner =  findMember(partnerId);

        ChatRoom chatRoom = null;
        //채팅방 유무 체크
        if(user.getId() > partnerId){
            chatRoom = chatRoomRepository.findChatRoomByPubAndSub(partner,user)
                    .orElseGet(()->createRoom(partner, user));

        }else{
            chatRoom = chatRoomRepository.findChatRoomByPubAndSub(user,partner)
                    .orElseGet(()->createRoom(user, partner));
        }

        //채팅 불러오기

        List<ChatMessage> chatMessageList = chatRepository.findAllByChatRoom(chatRoom);
        GetMSGByPartnerIdRes getMSGByPartnerIdRes = new GetMSGByPartnerIdRes();
        getMSGByPartnerIdRes.setChatMessageList(chatMessageList);
        getMSGByPartnerIdRes.setRoomId(chatRoom.getId());

        return getMSGByPartnerIdRes;
    }

    //채팅방접속 - 유저상세보기에서
    @Override
    public List<ChatMessage> getMessageByRoomId(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(roomId)
                .orElseThrow(()->new CustomException(ErrorCode.CHATROOM_NOT_FOUND));

        return chatRepository.findAllByChatRoom(chatRoom);
    }

    //채팅 저장
    @Override
    @Transactional
    public void saveMessage(ChatMessageReq messageReq) {
        ChatMessage chatMessage = new ChatMessage();

        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(messageReq.getRoomId())
                .orElseThrow(()->new CustomException(ErrorCode.CHATROOM_NOT_FOUND));

        String message = messageReq.getMessage();
        Long sender = messageReq.getSender();
        LocalDateTime time = messageReq.getTime();

//        if(message.isEmpty()) message = "";
//        if(sender==null){
//            throw new CustomException(ErrorCode.CHATROOM_NOT_FOUND);
//        }
//        if(time.)

        chatMessage.setChatRoom(chatRoom);
        chatMessage.setMessage(message);
        chatMessage.setSender(sender);
        chatMessage.setTime(time);

        chatRepository.save(chatMessage);

        chatRoom.setLatestMessageTime(time);

        System.out.println("채팅저장완료.");
    }

    public User findMember(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public ChatRoom createRoom(User user1, User user2){
        ChatRoom chatRoom = new ChatRoom(user1, user2, LocalDateTime.now());
        chatRoomRepository.save(chatRoom);

        return  chatRoom;
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
