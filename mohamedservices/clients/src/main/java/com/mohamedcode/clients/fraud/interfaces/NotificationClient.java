package com.mohamedcode.clients.fraud.interfaces;

import com.mohamedcode.clients.fraud.pojos.notification.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification")
public interface NotificationClient {
    @PostMapping("api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
