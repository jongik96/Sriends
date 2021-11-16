package com.project.autonomous.chat.entity;

import com.project.autonomous.common.entity.BaseEntity;
import com.project.autonomous.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="pub")
    private User pub;

    @ManyToOne
    @JoinColumn(name="sub")
    private User sub;

    private LocalDateTime latestMessageTime;
//    private boolean pubState;
//    private boolean subState;

//    public static ChatRoom create(String name) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }

}
