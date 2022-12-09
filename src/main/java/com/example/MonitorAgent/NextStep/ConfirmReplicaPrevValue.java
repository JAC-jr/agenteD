package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.models.V1Pod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConfirmReplicaPrevValue {

    @Autowired
    ApiReplica apiReplica;
    @Autowired
    ApiReplicaRepository apiReplicaRepository;

    public void searchItemFromDb (Integer father, V1Pod item){

       List<ApiReplica> replicaList = apiReplicaRepository.findAllByFatherApiId(father);

        for (ApiReplica apiReplica : replicaList) {
            if (Objects.equals(item.getMetadata().getName(), apiReplica.getReplica_name())){
                apiReplica.setReplica_last_status(apiReplica.getReplica_status());
                apiReplica.setReplica_status(item.getStatus().toString());
                apiReplica.setPrevious_replica_date(apiReplica.getReplica_date());
            }

        }
    }

    public ApiReplica apiReplicaBuilder (V1Pod item){

        return null;
    }
}