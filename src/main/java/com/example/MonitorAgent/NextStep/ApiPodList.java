package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.util.List;
import java.util.Objects;

@Service
public class ApiPodList {
    Logger logger = LoggerFactory.getLogger(ApiPodList.class);
    @Autowired RestTemplate restTemplate;
    @Autowired ApiReplicaRepository apiReplicaRepository;
    @Autowired ConfirmReplicaPrevValue confirmReplicaPrevValue;
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


            logger.debug(" Invocando APIs ");

            V1PodList list = api.listNamespacedPod(nameSpace, null,
                    null, null, null, "app=" + serviceName, null, null, null,
                    5000, null);

            for (V1Pod item : list.getItems()) {
                ApiReplica apiReplica = new ApiReplica();
                cont_items++;
                response = restTemplate.exchange("https://" + item.getStatus().getPodIP() + baseUrl,
                        HttpMethod.GET, requestEntity, Object.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    state++;
                }
                logger.info("response={}", response);
                logger.info("item = {} , status {}", item.getMetadata().getName(), response.getStatusCode());

                apiReplica.setApiId(apiID);
                apiReplica.setReplica_name(item.getMetadata().getName());
                apiReplica.setReplicaIp(item.getStatus().getPodIP());
                apiReplica.setReplica_status(response.getStatusCode().toString());
                apiReplica.setReplica_date(item.getMetadata().getCreationTimestamp().toString());

                if (){
                    ApiReplica PREV = apiReplicaRepository.findAllByReplicaIp(apiReplica.getReplicaIp());
                    apiReplica.setReplica_last_ip(PREV.getReplicaIp());
                    apiReplica.setReplica_previous_name(PREV.getReplica_name());
                    apiReplica.setReplica_last_status(PREV.getReplica_status());
                    apiReplica.setPrevious_replica_date(PREV.getReplica_date());
                    apiReplicaRepository.save(apiReplica);

                } else {
                    apiReplicaRepository.save(apiReplica);
                    logger.info("replica----- {}", apiReplica.getReplicaIp());
                }
            }
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
