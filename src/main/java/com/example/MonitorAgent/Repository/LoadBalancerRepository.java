package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.LoadBalancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadBalancerRepository extends JpaRepository<LoadBalancer, Integer> {
    List<LoadBalancer> findAllByApplicationId(Integer applicationId);
}