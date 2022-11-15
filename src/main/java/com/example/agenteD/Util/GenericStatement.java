package com.example.agenteD.Util;

import com.example.agenteD.Entity.*;
import com.example.agenteD.Repository.*;
import com.example.agenteD.Service.ApiService;
import com.example.agenteD.Service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GenericStatement{
    Logger logger = LoggerFactory.getLogger(GenericStatement.class);
    @Autowired ApiRepository apiRepository;
    @Autowired ApplicationRepository applicationRepository;
    @Autowired IntegrationRepository integrationRepository;
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired PersistenceRepository persistenceRepository;
    @Autowired ServiceRepository serviceRepository;
    @Autowired
    ApiService apiService;
    @Autowired
    ApplicationService applicationService;

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Api>> apiCompletableFuture() throws InterruptedException{
        logger.info("Looking up api info");
        List<Api> result = apiRepository.findAll();
        Thread.sleep(1000);
        result.forEach(api -> {
            logger.info("{}", api);
            apiService.objetResponse(api);
        });
        return CompletableFuture.completedFuture(result);
    }
    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Application>> applicationCompletableFuture() throws InterruptedException {
        logger.info("Looking up application info");
        List<Application> result = applicationRepository.findAll();

        result.forEach(application -> {
            logger.info("{}", application);
            applicationService.objetResponse(application);
        });
        return CompletableFuture.completedFuture(result);
    }

   /* @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Integration>> integrationCompletableFuture() throws InterruptedException{
        logger.info("Looking up integration info");
        List<Integration> result = integrationRepository.findAll();

        result.forEach(p -> {
            logger.info("{}", p);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<LoadBalancer>> loadBalancerCompletableFuture() throws InterruptedException{
        logger.info("Looking up loadBalancer info");
        List<LoadBalancer> result = loadBalancerRepository.findAll();

        result.forEach(p -> {
            logger.info("{}", p);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Persistence>> persistenceCompletableFuture() throws InterruptedException{
        logger.info("Looking up Persistence info");
        List<Persistence> result = persistenceRepository.findAll();

        result.forEach(p -> {
            logger.info("{}", p);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<com.example.agenteD.Entity.Service>> serviceCompletableFuture() throws InterruptedException{
        logger.info("Looking up service info");
        List<com.example.agenteD.Entity.Service> result = serviceRepository.findAll();

        result.forEach(p -> {
            logger.info("{}", p);
        });
        return CompletableFuture.completedFuture(result);
    }

    */

}

