//package com.project.autonomous.notification.controller;
//
//import com.project.autonomous.notification.service.NotificationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/notice")
//@RequiredArgsConstructor
//public class NotificationController {
//
//    private final NotificationService notificationService;
//
//    @MessageMapping("/{userId}")
//    public void connect(@DestinationVariable("userId") Long userId) {
//
//    }
//
//}
