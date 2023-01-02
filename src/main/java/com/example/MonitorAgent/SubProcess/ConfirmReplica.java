package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Entity.ServiceReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import com.example.MonitorAgent.Repository.ServiceReplicaRepository;
import io.kubernetes.client.openapi.models.V1Pod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmReplica {
    Logger logger = LoggerFactory.getLogger(ConfirmReplica.class);
    @Autowired ApiReplicaRepository apiReplicaRepository;
    @Autowired ReplicaRegistry replicaRegistry;
    @Autowired ServiceReplicaRepository serviceReplicaRepository;
//-------------------------------------------APIs---------------------------------------------------------
    public void ConfirmApiReplicaExist(V1Pod pod, Integer apiID, ResponseEntity<Object> response, LocalDateTime testTime) {

        ApiReplica previousReplicaRegistry = apiReplicaRepository.findAllByReplicaIpAndActualState(
                pod.getStatus().getPodIP(), true);

        if (previousReplicaRegistry == null) {
            replicaRegistry.newApiReplicaRegistry(pod, apiID, response, testTime);

        } else {
            replicaRegistry.updateApiReplicaRegistry(pod, apiID, response, previousReplicaRegistry, testTime);
        }
    }

    public void confirmApiActualState (ArrayList<String> actualPods, Integer apiID){

        List<ApiReplica> actualReplicaList = apiReplicaRepository.findAllByApiIdAndActualState(apiID, true);

        for ( ApiReplica replica : actualReplicaList ) {
            if (!actualPods.contains(replica.getReplicaIp())) {
                replica.setActualState(false);
                apiReplicaRepository.save(replica);
            }
        }
    }

    //-------------------------------------------SERVICES--------------------------------------------------------------

    public void ConfirmServiceReplicaExist(V1Pod pod, Integer serviceId, ResponseEntity<Object> response, LocalDateTime testTime) {

        ServiceReplica previousReplicaRegistry = serviceReplicaRepository.findAllByReplicaIpAndActualState(
                pod.getStatus().getPodIP(), true);

        if (previousReplicaRegistry == null) {
            replicaRegistry.newServiceReplicaRegistry(pod, serviceId, response, testTime);

        } else {
            replicaRegistry.updateServiceReplicaRegistry(pod, serviceId, response, previousReplicaRegistry, testTime);
        }
    }

    public void confirmServiceActualState (ArrayList<String> actualPods, Integer apiID){

        List<ServiceReplica> actualReplicaList = serviceReplicaRepository.findAllByServiceIdAndActualState(apiID, true);

        for ( ServiceReplica replica : actualReplicaList ) {
            if (!actualPods.contains(replica.getReplicaIp())) {
                replica.setActualState(false);
                serviceReplicaRepository.save(replica);
            }
        }
    }
}