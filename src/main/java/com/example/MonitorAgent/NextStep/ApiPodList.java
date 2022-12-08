package com.example.MonitorAgent.NextStep;

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
    public ResponseEntity<Object> apiKubeGet(String baseUrl, String nameSpace, String serviceName) {

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

            logger.debug(" Invocando APIs ");

            V1PodList list = api.listNamespacedPod(nameSpace, null,
                    null, null, null, "app="+serviceName, null, null, null,
                    5000, null );

            for (V1Pod item : list.getItems()) {

                response = restTemplate.exchange("https://" + item.getStatus().getPodIP()+ baseUrl,
                        HttpMethod.GET, requestEntity, Object.class);
                logger.info("response {}", response);
                logger.info("status={}", response.getStatusCode());

            }
        } catch (Exception e) {
            logger.error(" failure method getQueue " + e.getMessage());
        }
        return null;
    }
}
