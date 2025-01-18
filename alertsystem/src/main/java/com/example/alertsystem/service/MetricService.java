package com.example.alertsystem.service;

import org.springframework.stereotype.Service;

@Service
public class MetricService {

    public double runMetricQuery(String metricQuery) {
        return Math.random() * 100;  // Simulating a metric query result
    }
}
