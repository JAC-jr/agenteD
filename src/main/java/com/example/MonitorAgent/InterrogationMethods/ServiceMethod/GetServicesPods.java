package com.example.MonitorAgent.InterrogationMethods.ServiceMethod;

import com.example.MonitorAgent.Entity.Servicio;
import com.example.MonitorAgent.Entity.ServicesReplica;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.ConfirmAndSaveApi;

import com.example.MonitorAgent.Repository.ServicesReplicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.json.JSONObject;
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
public class GetServicesPods {


        Logger logger = LoggerFactory.getLogger(GetServicesPods.class);
        @Autowired
        RestTemplate restTemplate;

        @Autowired
        ConfirmAndSaveServices confirmAndSaveServices;

        @Autowired
        ServicesReplicaRepository servicesReplicaRepository;

        @Autowired ResponseStatus responseStatus;


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
                state = 0;
                cont_items = 0;
                ArrayList<String> actualist = new ArrayList<>();
                LocalDateTime testTime = null;




                logger.info(" Invocando services ");

                V1PodList list = api.listNamespacedPod(nameSpace, null,
                        null, null, null,
                        "app=" + labelApp, null, null,
                        null, 5000, null);

                for (V1Pod item : list.getItems()) {
                    cont_items++;
                ServicesReplica previous_replica_services = servicesReplicaRepository.
                        findAllByServicesReplicaIpAndServicesReplicaActualState(item.getStatus().getPodIP(), true);

                    try {
                        response = restTemplate.exchange("http://" + item.getStatus().getPodIP() + UrlServices, HttpMethod.GET, requestEntity, Object.class);
                        testTime = LocalDateTime.now();


                        logger.info(" Response: " + response.getBody().toString());


                    } catch (RestClientException e) {

                        logger.error("conexión timeout a replica ({}), ip ({})", item.getMetadata().getName(), item.getStatus().getPodIP());

                        if(response == null){
                            response = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
                        }

                        if (previous_replica_services== null) {

                            confirmAndSaveServices.newServiceReplicaRegistry(item, serviceId, response, testTime);

                        } else {
                            confirmAndSaveServices.prevReplicaBuilder(item, serviceId, response, previous_replica_services, testTime);
                        }
                        break;
                    }

                    if (previous_replica_services == null) {
                       confirmAndSaveServices.newServiceReplicaRegistry(item, serviceId, response, testTime);



                        if (responseStatus.checkstatus(response).equals("OK")) {
                            state++;
                        }

                    } else {
                        confirmAndSaveServices.prevReplicaBuilder(item, serviceId, response, previous_replica_services, testTime);
                        if (responseStatus.checkstatus(response).equals("OK")) {
                            state++;
                        }
                    }

                    logger.info("item = {} , status {}", item.getMetadata().getName(), responseStatus.checkstatus(response));
                    logger.info("Replica IP= " + item.getStatus().getPodIP());
                    actualist.add(item.getStatus().getPodIP());

                }
                confirmAndSaveServices.confirmActualState(serviceId, actualist);


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
