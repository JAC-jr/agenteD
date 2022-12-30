package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.VserverRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VserverRegistryRepository extends JpaRepository<VserverRegistry, Integer> {
}
