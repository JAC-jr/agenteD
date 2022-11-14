package com.example.agenteD.Repository;

import com.example.agenteD.Entity.Persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistenceRepository extends JpaRepository<Persistence, Integer> {
}