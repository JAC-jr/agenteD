package com.example.MonitorAgent.InterrogationMethods.ApiMethod;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class GetApiPods {
    Logger logger = LoggerFactory.getLogger(GetApiPods.class);
    @Autowired RestTemplate restTemplate;
    @Autowired ApiReplicaRepository apiReplicaRepository;
    @Autowired
    ConfirmAndSaveApi confirmAndSaveApi;

    public double apiKubeGet(String baseUrl, String nameSpace, String label_app, Integer apiID){

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
            ArrayList<String> actualist = new ArrayList<>();
            LocalDateTime testTime = null;

            logger.info(" Invocando APIs ");

            V1PodList list = api.listNamespacedPod(nameSpace, null,
                    null, null, null,
                    "app=" + label_app, null, null,
                    null, 5000, null);

            for (V1Pod item : list.getItems()) {
                cont_items++;
                ApiReplica previous_replica = apiReplicaRepository.findAllByReplicaIpAndActualState(
                        item.getStatus().getPodIP(), true);

                try {
                    response = restTemplate.exchange("https://" + item.getStatus().getPodIP() + baseUrl,
                            HttpMethod.GET, requestEntity, Object.class);
                    testTime = LocalDateTime.now();
                } catch (RestClientException e) {

                    logger.error("error en conexión a replica ({}), ip ({})----error {}",e
                            , item.getMetadata().getName(), item.getStatus().getPodIP());

                    if (previous_replica == null) {
                        confirmAndSaveApi.newApiReplicaRegistry(item, apiID, response, testTime);
                    } else {
                        confirmAndSaveApi.prevReplicaBuilder(item, apiID, response
                                , previous_replica, testTime);
                    }
                    break;
                }

                if (previous_replica == null) {
                    confirmAndSaveApi.newApiReplicaRegistry(item, apiID, response, testTime);
                    if (response.getStatusCode().is2xxSuccessful()) {
                    state++;
                    }

                } else {

                    confirmAndSaveApi.prevReplicaBuilder(item, apiID, response, previous_replica, testTime);
                    if (response.getStatusCode().is2xxSuccessful()) {
                    state++;
                    }
                }
                logger.info("item = {} , status {}", item.getMetadata().getName(), response.getStatusCode());
                logger.info("Replica IP= " + item.getStatus().getPodIP());
                actualist.add(item.getStatus().getPodIP());

            }
            confirmAndSaveApi.confirmActualState(apiID, actualist);
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
