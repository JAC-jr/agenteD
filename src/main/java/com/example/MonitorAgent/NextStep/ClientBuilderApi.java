package com.example.MonitorAgent.NextStep;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;

public class ClientBuilderApi {

    Logger logger = LoggerFactory.getLogger(ClientBuilderApi.class);

    public ApiClient getClient() {

        try {
            logger.debug(" Creando contexto ");

            ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(
                    new FileReader("./config")))
                    .build();

            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = getRestTemplateBypassingHostNameVerifcation();
            logger.debug(" Invocando API K8s ");
            V1PodList list = api.listNamespacedPod("pic", null,
                    null, null, null,
                    null, null, null, null,
                    5000, null);
            api.
            /*
             * ArrayList de pods de kubernetes por solicitud de app para llenar la hashmap
             * de las cola
             */
            for (V1Pod item : list.getItems()) {
                HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, headers);
                PicconectorSumaryDetail detail = null;
                String request = null;
                try {

                    /*Se busca primero con el metdo de los servicios nuevos*/
                    ResponseEntity<String> responseEntityDetailQueue = restTemplate.exchange(
                            "http://" + item.getStatus().getPodIP() + "/getCurrentQueue",
                            HttpMethod.GET, requestEntity, String.class);
                    logger.debug(" Response  " + "http://"
                            + item.getStatus().getPodIP()
                            + "/getCurrentQueue  "
                            + item.getMetadata().getName()
                            + " --- " + responseEntityDetailQueue.getBody());
                    request = responseEntityDetailQueue.getBody().split(":")[0].replace(
                            ".REQ", "");
                } catch (Exception e) {
                }
                try {
                    ResponseEntity<PicconectorSumaryDetail> responseEntityDetail = restTemplate.exchange(
                            "http://" + item.getStatus().getPodIP() + "/getSummaryQueueDetail",
                            HttpMethod.GET, requestEntity, PicconectorSumaryDetail.class);
                    logger.debug(" Response  " + "http://"
                            + item.getStatus().getPodIP()
                            + "/getSummaryQueueDetail  "
                            + item.getMetadata().getName()
                            + " --- " + responseEntityDetail.getBody()
                            + " Cola: " + responseEntityDetail.getBody().getQueuee());

                    detail = responseEntityDetail.getBody();
                    if (request == null)
                        request = String.format(requestQueue + "%03d", Integer.parseInt(detail.getQueuee()));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(" Falla " + item.getMetadata().getName() + " --- " + e.getMessage());
                    map.put(item.getMetadata().getName(), null);

                    continue;
                }
                Instance instance = new Instance();
                instance.setQueueName(request);
                instance.setName(item.getMetadata().getName());
                instance.setError(detail.getsummaryPicError());
                instance.setSuccess(detail.getsummaryPicSuccess());
                instance.setMeanRequestTime(0.0f);
                instance.setRequestSec(0.0f);
                instance.setUpdateTime(new Date());
                map.put(item.getMetadata().getName(), instance);


                logger.debug("***** Agregado al hash :" + item.getMetadata().getName() + " " + request + " " + instance.getName());

            }
        } catch (Exception e) {
            logger.error(" failure method getQueue " + e.getMessage());
        }
        return client;
    }
}
