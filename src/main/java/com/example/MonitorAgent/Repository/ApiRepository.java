package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<Api, Integer> {
}