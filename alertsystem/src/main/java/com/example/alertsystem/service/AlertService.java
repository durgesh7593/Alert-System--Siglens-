package com.example.alertsystem.service;

import com.example.alertsystem.entity.Alert;
import com.example.alertsystem.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Transactional
    public Alert createAlert(Alert alert) {
        alert.setCreatedAt(LocalDateTime.now());
        alert.setStatus("ACTIVE");  // Default status is ACTIVE
        return alertRepository.save(alert);
    }

    @Transactional
    public Alert updateAlert(Long id, Alert updatedAlert) {
        return alertRepository.findById(id)
                .map(alert -> {
                    alert.setInterval(updatedAlert.getInterval());
                    alert.setMetricQuery(updatedAlert.getMetricQuery());
                    alert.setConditionOperator(updatedAlert.getConditionOperator());
                    alert.setConditionValue(updatedAlert.getConditionValue());
                    alert.setNextScheduledAt(updatedAlert.getNextScheduledAt());
                    alert.setStatus(updatedAlert.getStatus());
                    alert.setUpdatedAt(LocalDateTime.now());
                    return alertRepository.save(alert);
                }).orElseThrow(() -> new IllegalArgumentException("Alert not found"));
    }

    @Transactional(readOnly = true)
    public List<Alert> getDueAlerts(LocalDateTime time) {
        return alertRepository.findByNextScheduledAtBefore(time);
    }

    @Async
    public void evaluateAlert(Alert alert) {
        boolean conditionMet = evaluateCondition(alert);
        alert.setStatus(conditionMet ? "TRIGGERED" : "ACTIVE");
        alert.setUpdatedAt(LocalDateTime.now());
        alertRepository.save(alert);
    }

    @Async
    public void evaluateBulkAlerts(List<Alert> alerts) {
        for (Alert alert : alerts) {
            evaluateAlert(alert);
        }
    }

    private boolean evaluateCondition(Alert alert) {
        double metricValue = runMetricQuery(alert.getMetricQuery());
        return evaluateAlertCondition(alert, metricValue);
    }

    private double runMetricQuery(String metricQuery) {
        return Math.random() * 100;  // Simulating a random metric value
    }

    private boolean evaluateAlertCondition(Alert alert, double metricValue) {
        switch (alert.getConditionOperator()) {
            case ">":
                return metricValue > alert.getConditionValue();
            case "<":
                return metricValue < alert.getConditionValue();
            case "==":
                return metricValue == alert.getConditionValue();
            default:
                throw new IllegalArgumentException("Invalid operator: " + alert.getConditionOperator());
        }
    }
    // Method to find alerts by interval in minutes
    public List<Alert> getAlertsByInterval(int intervalInMinutes) {
        return alertRepository.findByInterval(intervalInMinutes);  // Calling repository method
    }
}
