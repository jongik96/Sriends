package com.project.autonomous.notification.service;

import com.project.autonomous.notification.entity.NoticeType;
import com.project.autonomous.notification.entity.Notification;
import com.project.autonomous.notification.repository.NotificationRepository;
import com.project.autonomous.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void createNotification(User receiver, NoticeType type, Long postId) {
        String message = null;
        if(type.equals(NoticeType.MATCH)) {
            message = "내 매칭 게시글에 답글이 달렸습니다.";
        }

        if(type.equals(NoticeType.TEAM)) {
            message = "내 스렌즈 게시글에 답글이 달렸습니다.";
        }

        if(type.equals(NoticeType.COMMENT)){
            message = "내 댓글에 답글이 달렸습니다.";
        }

        notificationRepository.save(Notification.of(receiver, postId, type, message));
        simpMessagingTemplate.convertAndSend("/topic/" + receiver.getId(), message);
    }

}
