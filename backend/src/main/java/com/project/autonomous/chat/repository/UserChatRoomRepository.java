package com.project.autonomous.chat.repository;

import com.project.autonomous.chat.entity.UserChatRoom;
import com.project.autonomous.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
    List<UserChatRoom> findAllByUserChatRoomIdUser(User user);
}
