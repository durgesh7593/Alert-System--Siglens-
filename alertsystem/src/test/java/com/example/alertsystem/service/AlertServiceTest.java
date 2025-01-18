package com.example.alertsystem.service;

import com.example.alertsystem.service.AlertService;
import com.example.alertsystem.entity.Alert;
import com.example.alertsystem.repository.AlertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AlertServiceTest {

    @Mock
    private AlertRepository alertRepository;

    @InjectMocks
    private AlertService alertService;

    private Alert alert;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        alert = new Alert();
        alert.setId(1L);
        alert.setInterval(5);
        alert.setMetricQuery("last 1h: now: some_metric: avg");
        alert.setConditionOperator(">");
        alert.setConditionValue(50.0);
        alert.setStatus("ACTIVE");
    }

    @Test
    public void testCreateAlert() {
        when(alertRepository.save(any(Alert.class))).thenReturn(alert);
        Alert createdAlert = alertService.createAlert(alert);
        assertNotNull(createdAlert);
        assertEquals("ACTIVE", createdAlert.getStatus());
        verify(alertRepository, times(1)).save(alert);
    }

    @Test
    public void testUpdateAlert() {
        when(alertRepository.findById(1L)).thenReturn(java.util.Optional.of(alert));
        alert.setStatus("TRIGGERED");
        when(alertRepository.save(alert)).thenReturn(alert);
        Alert updatedAlert = alertService.updateAlert(1L, alert);
        assertNotNull(updatedAlert);
        assertEquals("TRIGGERED", updatedAlert.getStatus());
        verify(alertRepository, times(1)).save(alert);
    }

    @Test
    public void testFindAlertsByInterval() {
        when(alertRepository.findByInterval(5)).thenReturn(List.of(alert));
        List<Alert> alerts = alertService.getAlertsByInterval(5);
        assertNotNull(alerts);
        assertEquals(1, alerts.size());
        verify(alertRepository, times(1)).findByInterval(5);
    }
}
