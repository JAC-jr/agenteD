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
public class TestJpa implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(TestJpa.class);

    @Autowired
    GenericStatement genericStatement;

    public void run(String... args) throws Exception {


        CompletableFuture<List<Api>> apiResponse = genericStatement.apilistCompletableFuture();
        CompletableFuture<List<Application>> applicationResponse = genericStatement.applicationlistCompletableFuture();
        CompletableFuture<List<Integration>> integrationResponse = genericStatement.integrationlistCompletableFuture();
        CompletableFuture<List<LoadBalancer>> loadBalancerResponse = genericStatement.loadBalancerCompletableFuture();
        CompletableFuture<List<Persistence>> persistenceResponse = genericStatement.persistenceCompletableFuture();
        CompletableFuture<List<Service>> serviceResponse = genericStatement.serviceCompletableFuture();

        CompletableFuture.allOf(apiResponse, applicationResponse, integrationResponse,
                loadBalancerResponse, persistenceResponse, serviceResponse).join();

        apiResponse.(System.out.prinlist);
    }
}


