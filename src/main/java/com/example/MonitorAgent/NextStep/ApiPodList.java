package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ApiPodList {
    Logger logger = LoggerFactory.getLogger(ApiPodList.class);
    @Autowired RestTemplate restTemplate;
    @Autowired ApiReplicaRepository apiReplicaRepository;

    public double apiKubeGet(String baseUrl, String nameSpace, String serviceName, Integer apiID) {

        double cont_items = 0;
        double state = 0;
        try {
            logger.debug(" Creando contexto ");

            ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(
                            new FileReader("./config")))
                    .build();

            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();
            ResponseEntity<Object> response = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
            state = 0;
            cont_items = 0;

//            V1ReplicaSetList replicaSetList = new V1ReplicaSetList();
//            for (V1ReplicaSet replicaSet : replicaSetList.getItems()) {
//                logger.info("replicaSetList = {}",replicaSet.getStatus().getReplicas());
//            }
//            V1DeploymentList deploymentList = new V1DeploymentList();
//            ;
//            for (V1Deployment deployment : deploymentList.getItems()){
//                logger.info("deployment = {}",deployment.getStatus().getUpdatedReplicas());
//            }

            logger.info(" Invocando APIs ");

            V1PodList list = api.listNamespacedPod(nameSpace, null,
                    null, null, null, "app=" + serviceName, null, null, null,
                    5000, null);

            for (V1Pod item : list.getItems()) {
                ApiReplica apiReplica = new ApiReplica();
                cont_items++;

                ApiReplica previous_replica = apiReplicaRepository.findAllByReplicaIp(item.getStatus().getPodIP());

                try {
                    response = restTemplate.exchange("https://" + item.getStatus().getPodIP() + baseUrl,
                            HttpMethod.GET, requestEntity, Object.class);

                } catch (RestClientException e) {
                    logger.error("conexión timeout a replica ({}), ip ({})",item.getMetadata().getName(), item.getStatus().getPodIP());
                    if (previous_replica == null) {
                        apiReplica.setApiId(apiID);
                        apiReplica.setReplicaIp(item.getStatus().getPodIP());
                        apiReplica.setReplica_name(item.getMetadata().getName());
                        apiReplica.setReplica_creation_date(item.getMetadata().getCreationTimestamp().toString());
                        apiReplica.setMetadata(item.getMetadata().toString());
                        apiReplica.setReplica_status("connection timeout");
                        apiReplica.setReplica_last_test_date(LocalDateTime.now());
                        apiReplica.setReplica_last_fail_status_date(LocalDateTime.now());
                        apiReplicaRepository.save(apiReplica);
                    }
                    else{
                        previous_replica.setReplica_status("connection timeout");
                        previous_replica.setMetadata(item.getMetadata().toString());
                        previous_replica.setReplica_last_test_date(LocalDateTime.now());
                        previous_replica.setReplica_last_fail_status_date(LocalDateTime.now());
                        apiReplicaRepository.save(previous_replica);
                    }
                    break;
                }

                if (previous_replica == null) {

                    apiReplica.setApiId(apiID);
                    apiReplica.setReplicaIp(item.getStatus().getPodIP());
                    apiReplica.setReplica_name(item.getMetadata().getName());
                    apiReplica.setReplica_creation_date(item.getMetadata().getCreationTimestamp().toString());

                    apiReplica.setReplica_status(response.getStatusCode().toString());
                    apiReplica.setMetadata(item.getMetadata().toString());
                    apiReplica.setReplica_last_test_date(LocalDateTime.now());
                    logger.info("nueva replica de la api registrada");
                    logger.info("replica = {} ",apiReplica.getReplica_name());

                    if (response.getStatusCode().is2xxSuccessful()) {

                        apiReplica.setReplica_last_ok_status_date(LocalDateTime.now());
                        apiReplicaRepository.save(apiReplica);
                        state++;
                    } else {

                        apiReplica.setReplica_last_fail_status_date(LocalDateTime.now());
                        apiReplicaRepository.save(apiReplica);
                    }

                } else {

                    logger.info("ya existe registros de la replica {} de la api ", previous_replica.getReplica_name());
                    logger.info("actualizando datos de la replica {}", previous_replica.getReplica_name());

                    previous_replica.setReplica_status(response.getStatusCode().toString());
                    previous_replica.setMetadata(item.getMetadata().toString());
                    previous_replica.setReplica_last_test_date(LocalDateTime.now());

                    if (response.getStatusCode().is2xxSuccessful()) {

                        previous_replica.setReplica_last_ok_status_date(LocalDateTime.now());
                        apiReplicaRepository.save(previous_replica);
                        state++;
                    } else {

                        previous_replica.setReplica_last_fail_status_date(LocalDateTime.now());
                        apiReplicaRepository.save(previous_replica);
                    }
                }

                logger.info("item = {} , status {}", item.getMetadata().getName(), response.getStatusCode());
                logger.info("Replica IP= " + item.getStatus().getPodIP());

            }
        } catch (IOException | ApiException e) {
            throw new RuntimeException(e);
        }

        if (state == 0) {
            return 0;
        } else {
            return (state / cont_items) * 100;
        }
    }
}
