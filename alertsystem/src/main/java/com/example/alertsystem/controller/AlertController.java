package com.example.alertsystem.controller;

import com.example.alertsystem.entity.Alert;
import com.example.alertsystem.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    @PutMapping("/{id}")
    public Alert updateAlert(@PathVariable Long id, @RequestBody Alert alert) {
        return alertService.updateAlert(id, alert);
    }

//    @GetMapping("/{id}")
//    public Optional<Alert> getAlert(@PathVariable Long id) {
//        return alertService.getDueAlerts(id);
//    }

//    @GetMapping
//    public List<Alert> getAlerts() {
//        return alertService.getDueAlerts();
//    }
}
