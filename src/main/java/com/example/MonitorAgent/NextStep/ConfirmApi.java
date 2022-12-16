package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.models.V1Pod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConfirmApi {

    @Autowired ApiReplicaRepository apiReplicaRepository;

    public void confirmAvailability(Integer apiID){
        List<ApiReplica> list = apiReplicaRepository.findAllByApiId(apiID);
        for (ApiReplica replica : list) {
            LocalDateTime latestTime = replica.getReplica_last_test_date();
            LocalDateTime actualTime = LocalDateTime.now();

            if(actualTime.isAfter(latestTime.plusSeconds(5))){
            replica.setReplica_status( "no available");
            apiReplicaRepository.save(replica);
            }
        }
    }

    public ApiReplica apiReplicaBuilder (V1Pod item){

        return null;
    }
}