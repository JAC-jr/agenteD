package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.Repository.ApiRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class Prueba {
//    @Autowired ApiRepository apiRepository;
//    Logger logger = LoggerFactory.getLogger(Prueba.class);
//    public void invokeDeployment() throws IOException, ApiException, InterruptedException {
//
//        logger.info("ciclo deployment");
//
//            ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(
//                            new FileReader("./config")))
//                    .build();
//            logger.info("apiClient");
//            AppsV1Api Api = new AppsV1Api();
//            V1DeploymentList deploymentList = Api.listDeploymentForAllNamespaces(null, null,
//                    null, null, null, null, null,
//                    null, 5000,null);
//            logger.info("deploymentList");
//            for (V1Deployment deployment: deploymentList.getItems()) {
//                logger.info(deployment.getMetadata().getName());
//            }
//            wait(5000);
//
//
//    }
}
