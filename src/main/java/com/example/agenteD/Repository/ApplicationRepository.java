package com.example.agenteD.Repository;

import com.example.agenteD.Entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {


    Optional<Application> findByApplicationName(String applicationName);


}