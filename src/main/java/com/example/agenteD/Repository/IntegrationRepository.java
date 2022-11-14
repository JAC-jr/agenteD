package com.example.agenteD.Repository;

import com.example.agenteD.Entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long> {
}