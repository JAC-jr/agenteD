package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.IntegrationRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationRegistryRepository extends JpaRepository<IntegrationRegistry, Integer>{
}
