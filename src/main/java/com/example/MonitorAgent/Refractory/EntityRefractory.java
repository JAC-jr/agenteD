package com.example.MonitorAgent.Refractory;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EntityRefractory {
    Logger logger = LoggerFactory.getLogger(EntityRefractory.class);
    @Autowired ApiRepository apiRepository;
    @Autowired ApplicationRepository applicationRepository;
    @Autowired IntegrationRepository integrationRepository;
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired PersistenceRepository persistenceRepository;
    @Autowired ServiceRepository serviceRepository;
    @Autowired
    ObjetRefractory objetRefractory;
    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Api>> apiCompletableFuture() throws InterruptedException{
        logger.info("Looking up api info");
        List<Api> result = apiRepository.findAll();
        Thread.sleep(1000);
        result.forEach(api -> {
            logger.info("{}", api);
            //objetRefractory.objetResponse(api);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Application>> applicationCompletableFuture() throws InterruptedException {
        logger.info("Looking up application info");
        List<Application> result = applicationRepository.findAll();
        Thread.sleep(1000);
        result.forEach(application -> {
            logger.info("{}", application);
           // objetRefractory.objetResponse(application);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Integration>> integrationCompletableFuture() throws InterruptedException{
        logger.info("Looking up integration info");
        List<Integration> result = integrationRepository.findAll();

        result.forEach(integration -> {
            logger.info("{}", integration);
           // objetRefractory.objetResponse(integration);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<LoadBalancer>> loadBalancerCompletableFuture() throws InterruptedException{
        logger.info("Looking up loadBalancer info");
        List<LoadBalancer> result = loadBalancerRepository.findAll();

        result.forEach(loadBalancer -> {
            logger.info("{}", loadBalancer);
           // objetRefractory.objetResponse(loadBalancer);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<Persistence>> persistenceCompletableFuture() throws InterruptedException{
        logger.info("Looking up Persistence info");
        List<Persistence> result = persistenceRepository.findAll();

        result.forEach(persistence -> {
            logger.info("{}", persistence);
           // objetRefractory.objetResponse(persistence);
        });
        return CompletableFuture.completedFuture(result);
    }

    @Async("PrincipalThreadPoolTaskExecutor")
    public CompletableFuture<List<com.example.MonitorAgent.Entity.Service>> serviceCompletableFuture() throws InterruptedException{
        logger.info("Looking up service info");
        List<com.example.MonitorAgent.Entity.Service> result = serviceRepository.findAll();

        result.forEach(service -> {
            logger.info("{}", service);
           // objetRefractory.objetResponse(service);
        });
        return CompletableFuture.completedFuture(result);
    }
}

