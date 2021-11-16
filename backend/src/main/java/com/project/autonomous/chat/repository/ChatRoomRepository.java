package com.project.autonomous.chat.repository;

import com.project.autonomous.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllById(Long id);
//    boolean existsByChatRoomId(UserChatRoomId chatRoomId);
}
