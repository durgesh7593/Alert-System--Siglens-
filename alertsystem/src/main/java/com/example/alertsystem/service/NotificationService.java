package com.example.alertsystem.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    // Method to simulate sending a notification via logging
    public void sendNotification(String alertId, String message) {
        logger.info("Sending notification for Alert ID: {}. Message: {}", alertId, message);
    }
}
