package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.LoadBalancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadBalancerRepository extends JpaRepository<LoadBalancer, Integer> {
}