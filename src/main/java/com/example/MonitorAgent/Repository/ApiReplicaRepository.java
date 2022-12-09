package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.Api;
import com.example.MonitorAgent.Entity.ApiReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiReplicaRepository extends JpaRepository<ApiReplica, Integer> {

    List<ApiReplica> findAllByFatherApiId(Integer father_Id);
}
