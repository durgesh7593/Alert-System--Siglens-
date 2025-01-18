package com.example.alertsystem.service;

import com.example.alertsystem.entity.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertSchedulerService {

    private final AlertService alertService;

    @Autowired
    public AlertSchedulerService(AlertService alertService) {
        this.alertService = alertService;
    }

    @Scheduled(fixedRate = 60000)  // Every minute (fixed rate)
    public void evaluateAlerts() {
        LocalDateTime now = LocalDateTime.now();
        List<Alert> dueAlerts = alertService.getDueAlerts(now);
        alertService.evaluateBulkAlerts(dueAlerts);
    }
}
