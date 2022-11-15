package com.example.agenteD.Util;

import com.example.agenteD.Entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class FuturosCompletables implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(FuturosCompletables.class);

    @Autowired
    GenericStatement genericStatement;

    public void run(String... args) throws Exception {

            CompletableFuture<List<Api>> apiResponse = genericStatement.apiCompletableFuture();

       /* CompletableFuture<List<Application>> applicationResponse = genericStatement.applicationCompletableFuture();
        CompletableFuture<List<Integration>> integrationResponse = genericStatement.integrationCompletableFuture();
        CompletableFuture<List<LoadBalancer>> loadBalancerResponse = genericStatement.loadBalancerCompletableFuture();
        CompletableFuture<List<Persistence>> persistenceResponse = genericStatement.persistenceCompletableFuture();
        CompletableFuture<List<Service>> serviceResponse = genericStatement.serviceCompletableFuture();


            CompletableFuture.allOf(apiResponse, applicationResponse, integrationResponse,
                    loadBalancerResponse, persistenceResponse, serviceResponse).join();*/

    }
}


