package com.example.MonitorAgent.InterrogationMethods.ApiMethod;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
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
    public void newApiReplicaRegistry (V1Pod pod, Integer apiID, ResponseEntity<Object> response, LocalDateTime testTime){
        ApiReplica newReplicaRegistry = new ApiReplica();
        newReplicaRegistry.setApiId(apiID);
        newReplicaRegistry.setLabel_hash(pod.getMetadata().getLabels().get("pod-template-hash"));
        newReplicaRegistry.setReplicaIp(pod.getStatus().getPodIP());
        newReplicaRegistry.setReplica_name(pod.getMetadata().getName());
        newReplicaRegistry.setReplica_creation_date(pod.getMetadata().getCreationTimestamp().toString());
        newReplicaRegistry.setMetadata(pod.getMetadata().toString());
        newReplicaRegistry.setReplica_status(response.getStatusCode().toString());
        newReplicaRegistry.setReplica_last_test_date(testTime);
        newReplicaRegistry.setActualState(true);
        apiReplicaRepository.save(newReplicaRegistry);
        logger.info("nuevo registro de replica = {} ",newReplicaRegistry.getReplica_name());
    }

    public void updateApiReplicaRegistry (V1Pod pod, Integer apiID, ResponseEntity<Object> response, ApiReplica previousReplicaRegistry, LocalDateTime testTime){

        if (Objects.equals(previousReplicaRegistry.getReplica_status(), response.getStatusCode().toString())) {
            previousReplicaRegistry.setReplica_last_test_date(testTime);
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
}
