package com.robotdreams.notification.controller;

import com.robotdreams.notification.model.NotificationRequest;
import com.robotdreams.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("/notifications")
    public void sendNotification(@RequestBody NotificationRequest request){
        service.sendNotification(request);
    }

}
