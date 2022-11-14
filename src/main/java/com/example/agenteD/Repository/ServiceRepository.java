package com.example.agenteD.Repository;

import com.example.agenteD.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}