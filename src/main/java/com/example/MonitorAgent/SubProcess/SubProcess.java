package com.example.MonitorAgent.SubProcess;


import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.NextStep.NextStep;
import com.example.MonitorAgent.Repository.*;
import com.example.MonitorAgent.ResponseModel.ResponseBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired ApiRepository apiRepository;
    @Autowired IntegrationRepository integrationRepository;
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired PersistenceRepository persistenceRepository;
    @Autowired ServiceRepository serviceRepository;

    @Autowired NextStep nextStep;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<List<Api>> apiSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Api> result = apiRepository.findAllByApplicationId(applicationId);

        result.forEach(api -> {
            logger.info("application_Id = {}, Api_Id = {}, Test_interv = {}, " +
                    "status = {}",api.getApplicationId(),api.getApi_id()
                    ,api.getTestInterv(),api.getStatus());
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
                    integration.getApplicationId(),integration.getIntegration_id(),integration.getTestInterv()
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
                    "status = {}",loadBalancer.getApplicationId(),loadBalancer.getVserver_id(),loadBalancer.getTestInterv()
                    ,loadBalancer.getStatus());
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
        List<Persistence> result = persistenceRepository.findAllByApplicationId(applicationId);

        result.forEach(persistence -> {
            logger.info("application_Id = {}, Db_Id = {}, Test_interv = {}, " +
                    "status = {}",persistence.getApplicationId(),persistence.getDb_id(),persistence.getTestInterv()
                    ,persistence.getStatus());
            try {
                Thread.sleep(persistence.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<List<Servicio>> serviceSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Servicio> result = serviceRepository.findAllByApplicationId(applicationId);

        result.forEach(servicio -> {

            String serviceName = servicio.getServiceName();
            String port = servicio.getPort();
            Long testInterv = servicio.getTestInterv();
            String status = servicio.getStatus();
            String baseUrl = servicio.getTestUrl();

            try {
                long firstDate = System.currentTimeMillis();
                ResponseEntity<Object> response = nextStep.testUrl(baseUrl);
                long timeLapse = System.currentTimeMillis() - firstDate;
                logger.info("firstDate= {}" ,firstDate);
                logger.info("time lapse= {}" ,timeLapse);

                if (response.getHeaders().isEmpty()){

                    servicio.setStatus("null");
                    serviceRepository.save(servicio);
                    logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
                            servicio.getApplicationId(), servicio.getService_id(), status);
                }
                else {
                    servicio.setStatus(String.valueOf(response.getStatusCodeValue()));
                    serviceRepository.save(servicio);
                    logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
                            servicio.getApplicationId(), servicio.getService_id(), status);
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(servicio.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return CompletableFuture.completedFuture(result);
    }
}
