package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.LoadBalancerRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadBalancerRegistryRepository extends JpaRepository<LoadBalancerRegistry, Integer> {
}
