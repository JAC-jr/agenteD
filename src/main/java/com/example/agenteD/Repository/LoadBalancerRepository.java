package com.example.agenteD.Repository;

import com.example.agenteD.Entity.LoadBalancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadBalancerRepository extends JpaRepository<LoadBalancer, Integer> {
}