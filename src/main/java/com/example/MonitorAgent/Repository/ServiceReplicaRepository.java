package com.example.MonitorAgent.Repository;


import com.example.MonitorAgent.Entity.ServiceReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceReplicaRepository extends JpaRepository<ServiceReplica, Integer> {
    ServiceReplica findAllByReplicaIpAndActualState(String podIP, boolean actualState);
    List<ServiceReplica> findAllByServiceIdAndActualState(Integer ReplicaId, boolean actualState);
}


