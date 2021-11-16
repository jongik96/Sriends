package com.project.autonomous.notification.entity;

import com.project.autonomous.common.entity.BaseEntity;
import com.project.autonomous.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private Long postId;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    private String content;

    private Boolean checked;

    private LocalDateTime createAt;

    public static Notification of(User receiver, Long postId, NoticeType type, String content) {
        return Notification.builder()
            .receiver(receiver)
            .postId(postId)
            .type(type)
            .content(content)
            .checked(false)
            .createAt(LocalDateTime.now())
            .build();
    }

}
