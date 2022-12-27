package com.example.MonitorAgent.Repository;


import com.example.MonitorAgent.Entity.ServicesReplica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesReplicaRepository extends JpaRepository<ServicesReplica, Integer> {

    ServicesReplica findAllByServicesReplicaIpAndServicesReplicaActualState(String servicesReplicaIp, boolean servicesReplicaActualState);
    List<ServicesReplica> findAllByServicesReplicaIdAndServicesReplicaActualState(Integer servicesReplicaId, boolean servicesReplicaActualState);

}


