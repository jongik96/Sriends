package com.project.autonomous.chat.entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserChatRoom {

    @EmbeddedId
    private UserChatRoomId userChatRoomId;
}
