package com.example.agenteD.ComponentInit;

import com.example.agenteD.Entity.*;
import com.example.agenteD.Refractory.EntityRefractory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ComponentInit implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(ComponentInit.class);
    @Autowired
    EntityRefractory entityRefractory;

    public void run(String... args) throws Exception {

        CompletableFuture<List<Api>> apiResponse = entityRefractory.apiCompletableFuture();
        CompletableFuture<List<Application>> applicationResponse = entityRefractory.applicationCompletableFuture();
        CompletableFuture<List<Integration>> integrationResponse = entityRefractory.integrationCompletableFuture();
        CompletableFuture<List<LoadBalancer>> loadBalancerResponse = entityRefractory.loadBalancerCompletableFuture();
        CompletableFuture<List<Persistence>> persistenceResponse = entityRefractory.persistenceCompletableFuture();
        CompletableFuture<List<Service>> serviceResponse = entityRefractory.serviceCompletableFuture();


            CompletableFuture.allOf(apiResponse, applicationResponse, integrationResponse,
                    loadBalancerResponse, persistenceResponse, serviceResponse).join();

    }
}


