package com.project.autonomous.chat.repository;

import com.project.autonomous.chat.entity.ChatRoom;
import com.project.autonomous.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findChatRoomById(Long id);
    Optional<ChatRoom> findChatRoomByPubAndSub(User pub, User sub);
}
