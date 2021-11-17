package com.project.autonomous.chat.repository;

import com.project.autonomous.chat.entity.ChatMessage;
import com.project.autonomous.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findAllByChatRoom(ChatRoom chatRoom);
}
