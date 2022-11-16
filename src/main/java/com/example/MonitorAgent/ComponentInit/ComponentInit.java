package com.example.MonitorAgent.ComponentInit;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.Refractory.EntityRefractory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ComponentInit implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(ComponentInit.class);
    @Autowired
    EntityRefractory entityRefractory;

    @Value("${monitor.application}")
    private ArrayList<String> application;
    public void run(String... args) throws Exception {

        CompletableFuture<List<Api>> apiResponse = entityRefractory.apiCompletableFuture();
        CompletableFuture<List<Application>> applicationResponse = entityRefractory.applicationCompletableFuture();
        CompletableFuture<List<Integration>> integrationResponse = entityRefractory.integrationCompletableFuture();
        CompletableFuture<List<LoadBalancer>> loadBalancerResponse = entityRefractory.loadBalancerCompletableFuture();
        CompletableFuture<List<Persistence>> persistenceResponse = entityRefractory.persistenceCompletableFuture();
        CompletableFuture<List<Service>> serviceResponse = entityRefractory.serviceCompletableFuture();

logger.info("{}",application);
int num = application.size();
logger.info("{}",num);
            CompletableFuture.allOf(apiResponse, applicationResponse, integrationResponse,
                    loadBalancerResponse, persistenceResponse, serviceResponse).join();

    }
}


