package com.mohamedcode.clients.fraud.pojos.notification;

public record NotificationRequest(Long toCustomerId, String toCustomerName, String message) {
}
