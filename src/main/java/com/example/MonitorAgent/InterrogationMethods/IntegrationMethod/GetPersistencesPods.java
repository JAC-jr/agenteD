package com.example.MonitorAgent.InterrogationMethods.IntegrationMethod;

import com.example.MonitorAgent.InterrogationMethods.ServiceMethod.GetServicesPods;
import com.example.MonitorAgent.SubProcess.ConfirmReplica;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
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
public class GetPersistencesPods {
    Logger logger = LoggerFactory.getLogger(GetPersistencesPods.class);
    @Autowired RestTemplate restTemplate;
//    @Autowired ConfirmReplica confirmReplica;
    public double apiKubeGet(String UrlServices, String nameSpace, String labelApp, Integer serviceId) {

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

//----------------------------------------------------------------------------------------------
            state = 0;
            LocalDateTime testTime = null;
            ArrayList<String> actualPods = new ArrayList<String>();

            logger.info(" Invocando services ");

            V1PodList list = api.listNamespacedPod(nameSpace, null,
                    null, null, null,
                    "app=" + labelApp, null, null,
                    null, 5000, null);

            cont_items = list.getItems().size();

            for (V1Pod pod : list.getItems()) {

                actualPods.add(pod.getStatus().getPodIP());

                testTime = LocalDateTime.now();
                try {
                    response = restTemplate.exchange("http://" + pod.getStatus().getPodIP() + UrlServices, HttpMethod.GET, requestEntity, Object.class);

                    logger.info(" Response: " + response.getBody().toString());
                    logger.info("response status code = "+response.getStatusCode());
                } catch (RestClientException e) {

                    response = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);

                    logger.error("error en conexión a pod ({}), ip ({})----error {}",e
                            , pod.getMetadata().getName(), pod.getStatus().getPodIP());

                    confirmReplica.ConfirmServiceReplicaExist(pod, serviceId, response, testTime);
                    break;
                }

                logger.info("conexion a replica {} exirosa", pod.getMetadata().getName());

                confirmReplica.ConfirmServiceReplicaExist(pod, serviceId, response, testTime);
                if (response.getStatusCode().is2xxSuccessful()) {
                    logger.info("response status code = "+response.getStatusCode());
                    state++;
                }
            }

            confirmReplica.confirmServiceActualState(actualPods, serviceId);

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
