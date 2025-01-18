package com.example.alertsystem.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")

public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int interval;

    private String metricQuery;

    private String conditionOperator;

    private double conditionValue;

    private LocalDateTime lastEvaluatedAt;

    private LocalDateTime nextScheduledAt;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getMetricQuery() {
        return metricQuery;
    }

    public void setMetricQuery(String metricQuery) {
        this.metricQuery = metricQuery;
    }

    public String getConditionOperator() {
        return conditionOperator;
    }

    public void setConditionOperator(String conditionOperator) {
        this.conditionOperator = conditionOperator;
    }

    public double getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(double conditionValue) {
        this.conditionValue = conditionValue;
    }

    public LocalDateTime getLastEvaluatedAt() {
        return lastEvaluatedAt;
    }

    public void setLastEvaluatedAt(LocalDateTime lastEvaluatedAt) {
        this.lastEvaluatedAt = lastEvaluatedAt;
    }

    public LocalDateTime getNextScheduledAt() {
        return nextScheduledAt;
    }

    public void setNextScheduledAt(LocalDateTime nextScheduledAt) {
        this.nextScheduledAt = nextScheduledAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
