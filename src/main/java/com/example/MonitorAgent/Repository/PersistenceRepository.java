package com.example.MonitorAgent.Repository;


import com.example.MonitorAgent.Entity.Persistence;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersistenceRepository extends JpaRepository<Persistence, Integer> {
    List<Persistence> findByApplicationId(Integer applicationId);
}