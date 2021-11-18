package com.project.autonomous.notification.dto;

import com.project.autonomous.notification.entity.Notification;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NotificationRes {

    private Long notificationId;

    private Long postId;

    private String type;

    private String content;

    private Boolean checked;

    private LocalDateTime createAt;

    public static NotificationRes from(Notification notification) {
        return NotificationRes.builder()
            .notificationId(notification.getId())
            .postId(notification.getPostId())
            .type(notification.getType().toString())
            .content(notification.getContent())
            .checked(notification.getChecked())
            .createAt(notification.getCreateAt())
            .build();
    }

}
