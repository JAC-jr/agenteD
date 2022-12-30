package com.example.MonitorAgent.InterrogationMethods.ServiceMethod;


import com.example.MonitorAgent.Entity.ServicesReplica;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.ConfirmReplica;
import com.example.MonitorAgent.Repository.ServicesReplicaRepository;
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
public class ConfirmAndSaveServices {

  
        Logger logger = LoggerFactory.getLogger(ConfirmReplica.class);
        
        @Autowired ServicesReplicaRepository servicesReplicaRepository;

        @Autowired ResponseStatus responseStatus;
        
        public void confirmActualState(Integer servicesReplicaId, ArrayList<String> actualPods){
            List<ServicesReplica> listReplicas = servicesReplicaRepository.findAllByServicesReplicaIdAndServicesReplicaActualState(servicesReplicaId, true);
            for (ServicesReplica replica : listReplicas) {

                if(!actualPods.contains(replica.getServicesReplicaIp())) {
                    replica.setServicesReplicaActualState(false);
                    servicesReplicaRepository.save(replica);
                }
            }
        }

        public void newServiceReplicaRegistry (V1Pod item, Integer servicesReplicaId, ResponseEntity<Object> response, LocalDateTime testTime){

            ServicesReplica servicesReplica = new ServicesReplica();
            String status;

            status = responseStatus.checkstatus(response);

            servicesReplica.setServicesReplicaId(servicesReplicaId);
            servicesReplica.setServicesReplica_label_hash(item.getMetadata().getLabels().get("pod-template-hash"));
            servicesReplica.setServicesReplicaIp(item.getStatus().getPodIP());
            servicesReplica.setServicesReplica_name(item.getMetadata().getName());
            servicesReplica.setServicesReplica_creation_date(item.getMetadata().getCreationTimestamp().toString());
            servicesReplica.setMetadata(item.getMetadata().toString());
            servicesReplica.setServicesReplica_status(status);
            servicesReplica.setServicesReplica_last_test_date(testTime);
            servicesReplica.setServicesReplicaActualState(true);
            servicesReplicaRepository.save(servicesReplica);
            logger.info("nuevo registro de replica = {}",servicesReplica.getServicesReplica_name());

        }
        public void prevReplicaBuilder (V1Pod item, Integer serviceId, ResponseEntity<Object> response, ServicesReplica previous_replica_services, LocalDateTime testTime) {
            logger.info("ya existe registros de la replica {} ", previous_replica_services.getServicesReplica_name());
            logger.info("actualizando datos de la replica {}", previous_replica_services.getServicesReplica_name());

                String status = responseStatus.checkstatus(response);

                if (!status.equals(previous_replica_services.getServicesReplica_status())) {
                    logger.info("cambio de estado detectado");

                    previous_replica_services.setServicesReplicaActualState(false);
                    previous_replica_services.setServicesReplica_last_test_date(testTime);
                    servicesReplicaRepository.save(previous_replica_services);
                    logger.info("actualizacion de estado de la replica {}", previous_replica_services.getServicesReplica_name());

                    newServiceReplicaRegistry(item, serviceId, response, testTime);

                }

        }


    }

