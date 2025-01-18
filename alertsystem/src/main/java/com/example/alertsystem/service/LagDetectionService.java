package com.example.alertsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class LagDetectionService {

    private static final Logger logger = LoggerFactory.getLogger(LagDetectionService.class);

    public void logLag(LocalDateTime scheduledTime, LocalDateTime actualTime) {
        long lag = Duration.between(scheduledTime, actualTime).toMillis();
        if (lag > 5000) {  // If lag exceeds 5 seconds, log it
            logger.warn("Lag detected! Scheduled: {}, Actual: {}, Lag: {}ms", scheduledTime, actualTime, lag);
        }
    }
}
