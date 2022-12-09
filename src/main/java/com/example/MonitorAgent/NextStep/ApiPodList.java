package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Entity.ApiReplica;
import com.example.MonitorAgent.Repository.ApiReplicaRepository;
import com.example.MonitorAgent.Repository.ApiRepository;
import com.example.MonitorAgent.Threadfractory.ThreadFactory;
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

@Service
public class ApiPodList {

    Logger logger = LoggerFactory.getLogger(ApiPodList.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApiReplicaRepository apiReplicaRepository;
    public double apiKubeGet(String baseUrl, String nameSpace, String serviceName) {

        double items = 0;
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
            items = 0;

            logger.debug(" Invocando APIs ");

            V1PodList list = api.listNamespacedPod(nameSpace, null,
                    null, null, null, "app=" + serviceName, null, null, null,
                    5000, null);

            for (V1Pod item : list.getItems()) {

                items++;
                response = restTemplate.exchange("https://" + item.getStatus().getPodIP() + baseUrl,
                        HttpMethod.GET, requestEntity, Object.class);
                logger.info("item = {} , response {}", item.getMetadata().getName(), response);
                logger.info("status={}", response.getStatusCode());
                ApiReplica apiReplica = new ApiReplica();
                apiReplica.setReplica_name(item.getMetadata().getName());
                apiReplica.setReplica_ip(item.getStatus().getPodIP());
                apiReplica.setReplica_status(response.getStatusCode().toString());
                apiReplica.setReplica_date(item.getMetadata().getCreationTimestamp().toString());
                apiReplicaRepository.save(apiReplica);

                if (response.getStatusCode().is2xxSuccessful()) {
                    state++;
                }
            }
        } catch (Exception e) {
            logger.error(" failure method getQueue " + e.getMessage());
            continue;
        }
        return (items / state) * 100;
    }
}
