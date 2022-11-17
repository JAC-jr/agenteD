package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.Repository.ApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired
    ApiRepository apiRepository;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<List<Api>> apiSubProcessCompletableFuture(Application application) throws InterruptedException{
       /* Long result = api.getTestInterv();
        logger.info("application_id = {}, testInterv = {}",api.getApi_id(), result);
        Thread.sleep(result);*/
        Integer applicationId = application.getApplication_id();
        List<Api> result = apiRepository.findAllById(Collections.singleton(applicationId));

        result.forEach(api -> {
            logger.info("{}, {}, {}, {}",api.getApi_id(),api.getTestInterv(),api.getApplicationId(),api.getDescription());
            try {
                Thread.sleep(api.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


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
        logger.info("persistence_id = {}, testInterv = {}",persistence.getPersistence_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> serviceSubProcessCompletableFuture(com.example.MonitorAgent.Entity.Service service) throws InterruptedException{
        Long result = service.getTestInterv();
        logger.info("service_id = {}, testInterv = {}",service.getService_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }
}
