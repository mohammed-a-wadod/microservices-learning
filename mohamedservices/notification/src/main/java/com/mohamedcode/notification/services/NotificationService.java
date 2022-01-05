package com.mohamedcode.notification.services;

import com.mohamedcode.clients.fraud.pojos.notification.NotificationRequest;
import com.mohamedcode.notification.models.entities.Notification;
import com.mohamedcode.notification.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerName())
                .sender("M.Wadod ^__^ ")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build()
        );
    }
}
