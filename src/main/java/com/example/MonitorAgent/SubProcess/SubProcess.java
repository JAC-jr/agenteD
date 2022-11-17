package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired ApiRepository apiRepository;
    @Autowired IntegrationRepository integrationRepository;
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    PersistenceRepository persistenceRepository;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<List<Api>> apiSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Api> result = apiRepository.findAllByApplicationId(applicationId);

        result.forEach(api -> {
            logger.info("application_Id = {}, Api_Id = {}, Test_interv = {}, description = {}",api.getApplicationId(),api.getApi_id(),api.getTestInterv(),api.getDescription());
            try {
                Thread.sleep(api.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<List<Integration>> integrationSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Integration> result = integrationRepository.findAllByApplicationId(applicationId);

        result.forEach(integration -> {
            logger.info("application_Id = {}, Integration_Id = {}, Test_interv = {}, status = {}",
                    integration.getIntegration_id(),integration.getApplicationId(),integration.getTestInterv()
                    ,integration.getStatus());
            try {
                Thread.sleep(integration.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<List<LoadBalancer>> loadBalancerSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<LoadBalancer> result = loadBalancerRepository.findAllByApplicationId(applicationId);

        result.forEach(loadBalancer -> {
            logger.info("application_Id = {}, Vserver_Id = {}, Test_interv = {}, " +
                    "description = {}",loadBalancer.getAplicationId(),loadBalancer.getVserver_id(),loadBalancer.getTestInterv()
                    ,loadBalancer.getDescription());
            try {
                Thread.sleep(loadBalancer.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(result);
    }
    public CompletableFuture<List<Persistence>> persistenceSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Persistence> result = persistenceRepository.findByApplicationId(applicationId);

        result.forEach(persistence -> {
            logger.info("application_Id = {}, Persistence_Id = {}, Test_interv = {}, " +
                    "description = {}",persistence.getPersistence_id(),persistence.getPersistence_id(),persistence.getTestInterv()
                    ,persistence.getDescription());
            try {
                Thread.sleep(persistence.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<List<com.example.MonitorAgent.Entity.Service>> serviceSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<com.example.MonitorAgent.Entity.Service> result = serviceRepository.findByApplicationId(applicationId);

        result.forEach(service -> {
            logger.info("application_Id = {}, Service_Id = {}, Test_interv = {}, " +
                    "description = {}",service.getService_id(),service.getService_id(),service.getTestInterv()
                    ,service.getDescription());
            try {
                Thread.sleep(service.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<List<Integration>> integrationSubProcessCompletableFuture(List<Integration> integrationList) throws InterruptedException{
        integrationList.forEach(integration -> {
            logger.info("application_Id = {}, Integration_Id = {}, Test_interv = {}, status = {}",
                    integration.getIntegration_id(),integration.getApplicationId(),integration.getTestInterv()
                    ,integration.getStatus());
            try {
                Thread.sleep(integration.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(integrationList);
    }

}
