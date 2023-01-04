package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Integer> {
    List<Integration> findAllByApplicationId(Integer applicationId);
}