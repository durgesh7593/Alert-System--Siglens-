package com.example.alertsystem.repository;

import com.example.alertsystem.entity.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    private Alert alert;

    @BeforeEach
    public void setUp() {
        alert = new Alert();
        alert.setInterval(10);
        alert.setMetricQuery("last 2h: now: some_metric: avg");
        alert.setConditionOperator("<");
        alert.setConditionValue(60.0);
        alert.setStatus("ACTIVE");
        alert.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveAlert() {
        Alert savedAlert = alertRepository.save(alert);
        assertNotNull(savedAlert);
        assertEquals(10, savedAlert.getInterval());
    }

    @Test
    public void testFindAlertsByInterval() {
        alertRepository.save(alert);
        List<Alert> alerts = alertRepository.findByInterval(10);
        assertFalse(alerts.isEmpty());
        assertEquals(1, alerts.size());
    }

    @Test
    public void testDeleteAlert() {
        alertRepository.save(alert);
        alertRepository.deleteById(alert.getId());
        assertFalse(alertRepository.findById(alert.getId()).isPresent());
    }
}
