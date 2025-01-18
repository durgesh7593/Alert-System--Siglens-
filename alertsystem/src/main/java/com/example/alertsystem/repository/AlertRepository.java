package com.example.alertsystem.repository;

import com.example.alertsystem.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByInterval(int interval);

    List<Alert> findByNextScheduledAtBefore(LocalDateTime time);

    List<Alert> findAlertsByTimestampBetween(Instant startTime, Instant now);
}
