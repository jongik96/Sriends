package com.project.autonomous.notification.service;

import com.project.autonomous.notification.dto.MatchNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Transactional
    public void connectNotification(Long userId) {
        simpMessagingTemplate.convertAndSend("/topic/" + userId);
    }

    @Transactional
    public void sendNotification(MatchNotification matchNotification) {
        String message = matchNotification.getSender().getName() + "님이 " + "게시글에 댓글을 남기셨습니다.";
        simpMessagingTemplate.convertAndSend("/topic/" + matchNotification.getReceiver().getId(), message);
    }

}
