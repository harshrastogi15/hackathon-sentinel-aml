package com.harsh.azentio.alert.repository;

import com.harsh.azentio.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByStatusOrderByRiskScoreDesc(String status);
}