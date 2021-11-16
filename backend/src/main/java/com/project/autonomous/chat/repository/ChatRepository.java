package com.project.autonomous.chat.repository;

import com.project.autonomous.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

//    List<ChatMessage> findAllByPubAndSub(User pub, User sub);
}
