package com.example.agenteD.SubProcess;

import com.example.agenteD.Entity.*;
import com.example.agenteD.Repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired
    ApplicationRepository applicationRepository;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<Long> apiSubProcessCompletableFuture(Api api) throws InterruptedException{
        Long result = api.getTestInterv();
        logger.info("api_id = {}, testInterv = {}",api.getApi_id(), result);
        Thread.sleep(result);

    return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> applicationSubProcessCompletableFuture(Application application) throws InterruptedException{
        Long result = application.getTestInterv();
        logger.info("application_id = {}, testInterv = {}",application.getApplication_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> integrationSubProcessCompletableFuture(Integration integration) throws InterruptedException{
        Long result = integration.getTestInterv();
        logger.info("integration_id = {}, testInterv = {}",integration.getIntegration_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> loadBalancerSubProcessCompletableFuture(LoadBalancer loadBalancer) throws InterruptedException{
        Long result = loadBalancer.getTestInterv();
        logger.info("loadBalancer_id = {}, testInterv = {}",loadBalancer.getVserver_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> persistenceSubProcessCompletableFuture(Persistence persistence) throws InterruptedException{
        Long result = persistence.getTestInterv();
        logger.info("persistence_id = {}, testInterv = {}",persistence.getDb_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> serviceSubProcessCompletableFuture(com.example.agenteD.Entity.Service service) throws InterruptedException{
        Long result = service.getTestInterv();
        logger.info("service_id = {}, testInterv = {}",service.getService_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }
}
