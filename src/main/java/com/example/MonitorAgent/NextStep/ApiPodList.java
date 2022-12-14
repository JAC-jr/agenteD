package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.proto.V1Apps;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;

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
            V1ReplicaSet replicaSet = new V1ReplicaSet();
            CoreV1Api api = new CoreV1Api();
            ResponseEntity<Object> response = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
            state = 0;
            cont_items = 0;

            V1ReplicaSetList replicaSetList = new V1ReplicaSetList();

            V1D
            logger.debug(" Invocando APIs ");


//            V1PodList list = api.listNamespacedPod(nameSpace, null,
//                    null, null, null, "app=" + serviceName, null, null, null,
//                    5000, null);

            for (V1Pod item : list.getItems()) {
//                ApiReplica apiReplica = new ApiReplica();
//                cont_items++;
//                response = restTemplate.exchange("https://" + item.getStatus().getPodIP() + baseUrl,
//                        HttpMethod.GET, requestEntity, Object.class);
//                if (response.getStatusCode().is2xxSuccessful()) {
//                    state++;
//                }
//                logger.info("response={}", response);
//                logger.info("item = {} , status {}", item.getMetadata().getName(), response.getStatusCode());
//                logger.info("Replica IP= " + item.getStatus().getPodIP());
//
//                ApiReplica prev = apiReplicaRepository.findAllByReplicaIp(item.getStatus().getPodIP());
//                if (prev == null) {
//                        apiReplica.setApiId(apiID);
//                        apiReplica.setReplica_name(item.getMetadata().getName());
//                        apiReplica.setReplicaIp(item.getStatus().getPodIP());
//                        apiReplica.setReplica_status(response.getStatusCode().toString());
//                        apiReplica.setReplica_date(item.getMetadata().getCreationTimestamp().toString());
//                        apiReplicaRepository.save(apiReplica);logger.info("replica new ip= {}", apiReplica.getReplicaIp());
//                }
//                else {
////                    if (Objects.equals(prev.getReplicaIp(), item.getStatus().getPodIP())) {
//                        prev.setReplica_last_ip(prev.getReplicaIp());
//                        prev.setReplica_previous_name(prev.getReplica_name());
//                        prev.setReplica_last_status(prev.getReplica_status());
//                        prev.setPrevious_replica_date(prev.getReplica_date());
//                        prev.setApiId(apiID);
//                        prev.setReplica_name(item.getMetadata().getName());
//                        prev.setReplicaIp(item.getStatus().getPodIP());
//                        prev.setReplica_status(response.getStatusCode().toString());
//                        prev.setReplica_date(item.getMetadata().getCreationTimestamp().toString());
//                        apiReplicaRepository.save(prev);
//                        logger.info("prev ip = {}", prev.getReplicaIp());
//                        logger.info("new ip = {}", item.getStatus().getPodIP());
//                }
//            }
        } catch (Exception e) {
            logger.error(" failure method getQueue " + e.getMessage());
        }
        if (state == 0){
            return 0;
        }else {
            return (cont_items / state) * 100;
        }
    }
}
