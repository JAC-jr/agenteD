package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    Optional<Application> getIdByApplicationName(String applicationName);
}