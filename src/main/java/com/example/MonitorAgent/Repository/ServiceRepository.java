package com.example.MonitorAgent.Repository;


import com.example.MonitorAgent.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    Optional<Service> findByApplicationId(Integer applicationId);
}