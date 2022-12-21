package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.models.V1Pod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfirmApi {
    Logger logger = LoggerFactory.getLogger(ConfirmApi.class);
    @Autowired ApiReplicaRepository apiReplicaRepository;
    Map<LocalDateTime, HttpStatus> history = new HashMap<>();

    public void confirmActualState(Integer apiID, ArrayList<String> actualPods){
        List<ApiReplica> listReplicas = apiReplicaRepository.findAllByApiIdAndActualState(apiID, true);
        for (ApiReplica replica : listReplicas) {

            if(!actualPods.contains(replica.getReplicaIp())) {
            replica.setActualState(false);
            apiReplicaRepository.save(replica);
            }
        }
    }

    public void newApiReplicaRegistry (V1Pod item, Integer apiID, ResponseEntity<Object> response, LocalDateTime testTime){
        ApiReplica apiReplica = new ApiReplica();
        apiReplica.setApiId(apiID);
        apiReplica.setReplicaIp(item.getStatus().getPodIP());
        apiReplica.setReplica_name(item.getMetadata().getName());
        apiReplica.setReplica_creation_date(item.getMetadata().getCreationTimestamp().toString());
        apiReplica.setMetadata(item.getMetadata().toString());
        apiReplica.setReplica_status(response.getStatusCode().toString());
        apiReplica.setReplica_last_test_date(testTime);
        apiReplica.setActualState(true);
        apiReplicaRepository.save(apiReplica);
        logger.info("nuevo registro de replica = {} ",apiReplica.getReplica_name());
    }
    public void prevReplicaBuilder (V1Pod item, Integer apiID, ResponseEntity<Object> response, ApiReplica previous_replica, LocalDateTime testTime){
        logger.info("ya existe registros de la replica {} de la api ", previous_replica.getReplica_name());
        logger.info("actualizando datos de la replica {}", previous_replica.getReplica_name());

        if (!response.getStatusCode().toString().equals(previous_replica.getReplica_status())) {
            logger.info("cambio de estado detectado");

            previous_replica.setActualState(false);
            apiReplicaRepository.save(previous_replica);

            newApiReplicaRegistry(item, apiID, response, testTime);
        }
    }
}