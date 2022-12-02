package com.example.MonitorAgent.NextStep;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;
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
public class ApiReplicaBuilder {

    Logger logger = LoggerFactory.getLogger(ApiReplicaBuilder.class);
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity<Object> apiKubeGet() {

        try {
            logger.debug(" Creando contexto ");

            ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(
                    new FileReader("./config")))
                    .build();

            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            logger.debug(" Invocando APIs ");
            V1ServiceList serviceList;
            V1PodList list = api.listNamespacedPod("bdvapi", null,
                    null, null, null,
                    null, null, null, null,
                    5000, null);

            /*
             * ArrayList de pods de kubernetes por solicitud de app para llenar la hashmap
             * de las cola
             */
            for (V1Pod item : list.getItems()) {
                HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, headers);
                String request = null;
                try {
                    //************Se busca primero con el metdo de los servicios nuevos******************

                    logger.info("metadata: {}", item.getMetadata());
                    ResponseEntity<String> responseEntityDetailQueue = restTemplate.exchange(
                            "http://" + item.getStatus().getPodIP()+"  " api,
                            HttpMethod.GET, requestEntity, String.class);
                    logger.debug(" Response  " + "http://"
                            + item.getStatus().getPodIP()
                            + "/getCurrentQueue  "
                            + item.getMetadata().getName()
                            + " --- " + responseEntityDetailQueue.getBody());
                    request = responseEntityDetailQueue.getBody().split(":")[0].replace(
                            ".REQ", "");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            logger.error(" failure method getQueue " + e.getMessage());
        }
        return null;
    }

//    private void getMonitoringEndpointFromService() throws ApiException, IOException {
//        ApiClient client = Config.defaultClient();
//        Configuration.setDefaultApiClient(client);
//        CoreV1Api api = new CoreV1Api();
//
//        V1ServiceList serviceList;
//        serviceList = api.listService("bdvapi", null,
//                null, null, null, null, null,
//                null, null, 5000 , null) ;
//
//        for (V1Service service : serviceList.getItems()) {
//            String serviceName = service.getMetadata().getName();
//            if (serviceName.toUpperCase().equals(DeploymentInfo.getMonitoringAgentService())) {
//                String clusterIP = service.getSpec().getClusterIP();
//                int port = service.getSpec().getPorts().get(0).getPort();
//                DeploymentInfo.setMonitoringAgentEndpoint("http://" + clusterIP + ":" + port);
//            }
//
//  }
}
