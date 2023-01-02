package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.GetApiPods;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.ConfirmAndSaveApiState;
import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.ConfirmAndSaveLoadBalancer;
import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.F5ResponseModel;
import com.example.MonitorAgent.InterrogationMethods.ServiceMethod.ConfirmAndSaveServiceState;
import com.example.MonitorAgent.InterrogationMethods.ServiceMethod.GetServicesPods;
import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.LoadBalancerCurl;
import com.example.MonitorAgent.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProcessThreads {
    @Autowired ApiRepository apiRepository;
    @Autowired IntegrationRepository integrationRepository;
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired PersistenceRepository persistenceRepository;
    @Autowired ServiceRepository serviceRepository;
    @Autowired GetServicesPods getServicesPods;
    @Autowired LoadBalancerCurl loadBalancerCurl;
    @Autowired ConfirmAndSaveLoadBalancer confirmAndSaveLoadBalancer;
    @Autowired GetApiPods getApiPods;
    @Autowired ConfirmAndSaveApiState confirmAndSaveApiState;
    @Autowired CalculateCommonValues calculateCommonValues;
    @Autowired ConfirmAndSaveServiceState confirmAndSaveServiceState;
    Logger logger = LoggerFactory.getLogger(ProcessThreads.class);

    //--------------------------------APIS----------------------------------------------------
    public CompletableFuture<List<Api>> apiSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Api> result = apiRepository.findAllByApplicationId(applicationId);

        result.forEach(api -> {
            Integer apiID = api.getApi_id();
            String baseUrl = api.getBase_url();
            String nameSpace = api.getNameSpace();
            String label_app = api.getLabel_app();

            LocalDateTime testTime = LocalDateTime.now();
            long firstDate = System.currentTimeMillis();
            double response = getApiPods.apiKubeGet(baseUrl, nameSpace, label_app, apiID);
            long timeLapse = System.currentTimeMillis() - firstDate;
            logger.info("time lapse= {}" ,timeLapse);

            String status = calculateCommonValues.statusApi(response, api);

            confirmAndSaveApiState.confirmAndSaveApi(api, status, testTime, response, timeLapse);

            try {
                Thread.sleep(api.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    return CompletableFuture.completedFuture(result);
    }
//-----------------------------------LOAD BALANCER (F5)--------------------------------------------------------
    public CompletableFuture<List<LoadBalancer>> loadBalancerSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<LoadBalancer> result = loadBalancerRepository.findAllByApplicationId(applicationId);

        result.forEach(loadBalancer -> {
            String baseUrl = loadBalancer.getUrlServer();
            String Json = loadBalancer.getJson();

            try{
                long firstDate = System.currentTimeMillis();
                LocalDateTime testTime = LocalDateTime.now();
                ResponseEntity<F5ResponseModel> response = loadBalancerCurl.testLoadBalancer(baseUrl,Json,loadBalancer);
                long timeLapse = System.currentTimeMillis() - firstDate;
                logger.info("time lapse= {}" ,timeLapse);
                confirmAndSaveLoadBalancer.confirmAndSaveF5(testTime, response, loadBalancer);
                loadBalancer.setResponse_time(timeLapse);

                if (response.getStatusCode().is2xxSuccessful()){
                    logger.info("respuesta del F5 exitosa");
                    loadBalancer.setSuccessfulConsecutiveTest(loadBalancer.getSuccessfulConsecutiveTest()+1);
                    loadBalancer.setFailedConsecutiveTest(0);
                    loadBalancer.setHistorySuccessfulTest(loadBalancer.getHistorySuccessfulTest()+1);
                }
                else {

                    logger.info("error al recibir respuesta");
                    loadBalancer.setSuccessfulConsecutiveTest(0);
                    loadBalancer.setFailedConsecutiveTest(loadBalancer.getFailedConsecutiveTest()+1);
                    loadBalancer.setHistoryFailedTest(loadBalancer.getHistoryFailedTest()+1);
                }
                loadBalancerRepository.save(loadBalancer);

            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(loadBalancer.getTestInterv());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return CompletableFuture.completedFuture(result);
    }
//------------------------------------SERVICES-------------------------------------------------------
public CompletableFuture<List<Servicio>> serviceSubProcessCompletableFuture(Application application) throws InterruptedException{
    Integer applicationId = application.getApplication_id();
    List<Servicio> result = serviceRepository.findAllByApplicationId(applicationId);

    result.forEach(servicio -> {

        String urlServices = servicio.getTestUrl();
        String nameSpace = servicio.getNameSpace();
        String labelApp = servicio.getLabelApp();
        Integer serviceId = servicio.getServiceId();

        LocalDateTime testTime = LocalDateTime.now();
        long firstDate = System.currentTimeMillis();
        double response = getServicesPods.apiKubeGet(urlServices, nameSpace, labelApp, serviceId);
        long timeLapse = System.currentTimeMillis() - firstDate;
        logger.info("time lapse= {}" ,timeLapse);

        String status = calculateCommonValues.statusService(response, servicio);

        confirmAndSaveServiceState.confirmAndSaveService(servicio, status, testTime, response, timeLapse);

//        double firstDate = System.currentTimeMillis();
//        double response = getServicesPods.apiKubeGet(UrlServices, nameSpace, labelApp, serviceId);
//        getServicesPods.apiKubeGet(UrlServices, nameSpace, labelApp, serviceId);
//        double timeLapse = System.currentTimeMillis() - firstDate;
//        logger.info("time lapse= {}" ,timeLapse);
//
//        if (response ==0){
//
//            servicio.setStatus("sin replicas funcionales");
//            serviceRepository.save(servicio);
//            logger.info("{}",servicio);
//            logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
//                    servicio.getApplicationId(), servicio.getServiceId(), servicio.getStatus());
//            logger.info("error al recibir respuesta");
//        }
//        else {
//            servicio.setStatus(response+"%");
//            serviceRepository.save(servicio);
//            logger.info("{}",servicio.getStatus());
//            logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
//                    servicio.getApplicationId(), servicio.getServiceId(), servicio.getStatus());
//            logger.info("respuesta del servicio exitosa");
//        }
        try {
            Thread.sleep(servicio.getTestInterv());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    return CompletableFuture.completedFuture(result);
}
//-------------------------------PERSISTENCE----------------------------------------------------------
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
//-------------------------------------INTEGRATION--------------------------------------------------------
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
}
