package com.example.MonitorAgent.Repository;


import com.example.MonitorAgent.Entity.ServicesReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesReplicaRepository extends JpaRepository<ServicesReplica, Integer> {

    ServicesReplica findAllByServicesReplicaIpAndServicesReplicaActualState(String servicesReplicaIp, boolean servicesReplicaActualState);
    List<ServicesReplica> findAllByServicesReplicaIdAndServicesReplicaActualState(Integer servicesReplicaId, boolean servicesReplicaActualState);

}


