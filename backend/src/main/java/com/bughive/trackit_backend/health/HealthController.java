package com.bughive.trackit_backend.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class HealthController {

    @Autowired(required = false)
    private DataSource dataSource;

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "Backend ready");
        try {
            if (dataSource != null && dataSource.getConnection() != null) {
                status.put("db", "PostgreSQL connected " + dataSource.getConnection());
            } else {
                status.put("db", "PostgreSQL not available (mock)");
            }
        } catch (Exception e) {
            status.put("db", "PostgreSQL connection failed: " + e.getMessage());
        }
        return status;
    }
}
