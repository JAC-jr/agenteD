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
import java.util.Objects;

@Service
public class ReplicaRegistry {
    Logger logger = LoggerFactory.getLogger(ReplicaRegistry.class);
    @Autowired ApiReplicaRepository apiReplicaRepository;
    @Autowired ServiceReplicaRepository serviceReplicaRepository;
    public void newApiReplicaRegistry (V1Pod pod, Integer apiID, ResponseEntity<Object> response, LocalDateTime testTime){
        ApiReplica newReplicaRegistry = new ApiReplica();
        newReplicaRegistry.setApiId(apiID);
        newReplicaRegistry.setLabel_hash(pod.getMetadata().getLabels().get("pod-template-hash"));
        newReplicaRegistry.setReplicaIp(pod.getStatus().getPodIP());
        newReplicaRegistry.setReplica_name(pod.getMetadata().getName());
        newReplicaRegistry.setCreation_date(pod.getMetadata().getCreationTimestamp().toString());
        newReplicaRegistry.setMetadata(pod.getMetadata().toString());
        newReplicaRegistry.setStatus(response.getStatusCode().toString());
        newReplicaRegistry.setLast_test_date(testTime);
        newReplicaRegistry.setActualState(true);
        apiReplicaRepository.save(newReplicaRegistry);
        logger.info("nuevo registro de replica = {} ",newReplicaRegistry.getReplica_name());
    }

    public void updateApiReplicaRegistry (V1Pod pod, Integer apiID, ResponseEntity<Object> response, ApiReplica previousReplicaRegistry, LocalDateTime testTime){

        if (Objects.equals(previousReplicaRegistry.getStatus(), response.getStatusCode().toString())) {
            previousReplicaRegistry.setLast_test_date(testTime);
            previousReplicaRegistry.setMetadata(pod.getMetadata().toString());
            logger.info("registro de replica actualizado = " + previousReplicaRegistry.getReplica_name());
        }
        else{
            logger.info("cambio en estado de replica");
            previousReplicaRegistry.setActualState(false);
            newApiReplicaRegistry(pod, apiID, response, testTime);
        }
        apiReplicaRepository.save(previousReplicaRegistry);
    }
//----------------------------------------------SERVICE----------------------------------------------------------------------
    public void newServiceReplicaRegistry (V1Pod pod, Integer apiID, ResponseEntity<Object> response, LocalDateTime testTime){
        ServiceReplica newReplicaRegistry = new ServiceReplica();
        newReplicaRegistry.setServiceId(apiID);
        newReplicaRegistry.setLabel_hash(pod.getMetadata().getLabels().get("pod-template-hash"));
        newReplicaRegistry.setReplicaIp(pod.getStatus().getPodIP());
        newReplicaRegistry.setReplica_name(pod.getMetadata().getName());
        newReplicaRegistry.setCreation_date(pod.getMetadata().getCreationTimestamp().toString());
        newReplicaRegistry.setMetadata(pod.getMetadata().toString());
        newReplicaRegistry.setStatus(response.getStatusCode().toString());
        newReplicaRegistry.setLast_test_date(testTime);
        newReplicaRegistry.setActualState(true);
        serviceReplicaRepository.save(newReplicaRegistry);
        logger.info("nuevo registro de replica = {} ",newReplicaRegistry.getReplica_name());
    }

    public void updateServiceReplicaRegistry (V1Pod pod, Integer serviceId, ResponseEntity<Object> response, ServiceReplica previousReplicaRegistry, LocalDateTime testTime){

        if (Objects.equals(previousReplicaRegistry.getStatus(), response.getStatusCode().toString())) {
            previousReplicaRegistry.setLast_test_date(testTime);
            previousReplicaRegistry.setMetadata(pod.getMetadata().toString());
            logger.info("registro de replica actualizado = " + previousReplicaRegistry.getReplica_name());
        }
        else{
            logger.info("cambio en estado de replica");
            previousReplicaRegistry.setActualState(false);
            newServiceReplicaRegistry(pod, serviceId, response, testTime);
        }
        serviceReplicaRepository.save(previousReplicaRegistry);
    }
}
