package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.PersistenceRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistenceRegistryRepository extends JpaRepository<PersistenceRegistry, Integer> {
}
