package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.ApiReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiReplicaRepository extends JpaRepository<ApiReplica, Integer> {
    ApiReplica findAllByReplicaIp(String replicaIp);
    List<ApiReplica> findAllByApiId(Integer apiId);
}
