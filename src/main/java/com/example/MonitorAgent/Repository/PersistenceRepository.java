package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.Persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersistenceRepository extends JpaRepository<Persistence, Integer> {
    List<Persistence> findByApplicationId(Integer applicationId);
}