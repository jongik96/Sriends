package com.project.autonomous.notification.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.notification.dto.NotificationRes;
import com.project.autonomous.notification.entity.NoticeType;
import com.project.autonomous.notification.entity.Notification;
import com.project.autonomous.notification.repository.NotificationRepository;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createNotification(User receiver, NoticeType type, Long postId) {
        String message = null;
        if (type.equals(NoticeType.MATCH)) {
            message = "내 매칭 게시글에 답글이 달렸습니다.";
        }

        if (type.equals(NoticeType.TEAM)) {
            message = "내 스렌즈 게시글에 답글이 달렸습니다.";
        }

        if (type.equals(NoticeType.COMMENT)) {
            message = "내 댓글에 답글이 달렸습니다.";
        }

        notificationRepository.save(Notification.of(receiver, postId, type, message));
        simpMessagingTemplate.convertAndSend("/topic/" + receiver.getId(), message);
    }

    // 사용자의 알림 리스트
    public List<NotificationRes> getNotification() {
        return notificationRepository.findAllByReceiverOrderByCreateAtDesc(
            findMember(SecurityUtil.getCurrentMemberId()))
            .stream()
            .map(NotificationRes::from)
            .collect(Collectors.toList());
    }

    // 확인하지 않은 알림 수
    public Long getUnCheckedCount() {
        return 1L;
    }

    // 알림을 확인
    public void checkNotice() {

    }

    // 알림 전체 확인

    // 알림 전체 제거

    public User findMember(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
