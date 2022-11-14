package com.example.agenteD.Util;

import com.example.agenteD.Entity.*;
import com.example.agenteD.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GenericStatement{

    Logger logger = LoggerFactory.getLogger(GenericStatement.class);
    @Autowired
    ApiRepository apiRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    IntegrationRepository integrationRepository;
    @Autowired
    LoadBalancerRepository loadBalancerRepository;
    @Autowired
    PersistenceRepository persistenceRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @Async
    public CompletableFuture<List<Api>> apilistCompletableFuture() throws InterruptedException{
        logger.info("Looking up api info");
        List<Api> results = apiRepository.findAll();
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        logger.info("{}", results);
        System.out.println();
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public CompletableFuture<List<Application>> applicationlistCompletableFuture() throws InterruptedException {
        logger.info("Looking up application info");
        List<Application> results = applicationRepository.findAll();
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public CompletableFuture<List<Integration>> integrationlistCompletableFuture() throws InterruptedException{
        logger.info("Looking up integration info");
        List<Integration> results = integrationRepository.findAll();
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        logger.info("{}", results);
        results.forEach(System.out::println);
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public CompletableFuture<List<LoadBalancer>> loadBalancerCompletableFuture() throws InterruptedException{
        logger.info("Looking up loadBalancer info");
        List<LoadBalancer> results = loadBalancerRepository.findAll();
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        logger.info("{}", results);
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public CompletableFuture<List<Persistence>> persistenceCompletableFuture() throws InterruptedException{
        logger.info("Looking up Persistence info");
        List<Persistence> results = persistenceRepository.findAll();
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        logger.info("{}", results);
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public CompletableFuture<List<com.example.agenteD.Entity.Service>> serviceCompletableFuture() throws InterruptedException{
        logger.info("Looking up service info");
        List<com.example.agenteD.Entity.Service> results = serviceRepository.findAll();
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        logger.info("{}", results);
        return CompletableFuture.completedFuture(results);
    }

}

