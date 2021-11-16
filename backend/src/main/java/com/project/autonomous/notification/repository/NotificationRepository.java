package com.project.autonomous.notification.repository;

import com.project.autonomous.notification.entity.Notification;
import com.project.autonomous.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverOrderByCreateAtDesc(User receiver);

}