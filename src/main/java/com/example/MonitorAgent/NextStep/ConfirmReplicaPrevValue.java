package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.models.V1Pod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConfirmReplicaPrevValue {
    Logger logger = LoggerFactory.getLogger(ConfirmReplicaPrevValue.class);
    @Autowired
    ApiReplicaRepository apiReplicaRepository;

    public void searchItemFromDb (Integer apiID, V1Pod item){

//       List<ApiReplica> replicaList = apiReplicaRepository.findAllByApiId(apiID);
//        for (ApiReplica prev_replica : replicaList) {
//            if (prev_replica.getReplica_name() == item.getMetadata().getName()){
//                prev_replica.setReplica_previous_name(prev_replica.getReplica_name());
//                prev_replica.setReplica_last_ip(prev_replica.getReplica_ip());
//                prev_replica.setReplica_last_status(prev_replica.getReplica_status());
//                prev_replica.setPrevious_replica_date(prev_replica.getReplica_date());
//                apiReplicaRepository.save(prev_replica);
//            }
//        }
    }

    public ApiReplica apiReplicaBuilder (V1Pod item){

        return null;
    }
}