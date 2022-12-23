package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.GetApiPods;
import com.example.MonitorAgent.InterrogationMethods.ServiceMethod.ServiceCurl;
import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.LoadBalancerCurl;
import com.example.MonitorAgent.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired ServiceCurl serviceCurl;
    @Autowired LoadBalancerCurl loadBalancerCurl;
    @Autowired
    GetApiPods getApiPods;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);

    //--------------------------------APIS----------------------------------------------------
    public CompletableFuture<List<Api>> apiSubProcessCompletableFuture(Application application) throws InterruptedException{
        Integer applicationId = application.getApplication_id();
        List<Api> result = apiRepository.findAllByApplicationId(applicationId);

        result.forEach(api -> {
            Integer apiID = api.getApi_id();
            String baseUrl = api.getBase_url();
            String nameSpace = api.getNameSpace();
            String label_app = api.getLabel_app();

            Long firstDate = System.currentTimeMillis();
            double response = getApiPods.apiKubeGet(baseUrl, nameSpace, label_app, apiID);
            Long timeLapse = System.currentTimeMillis() - firstDate;
            logger.info("time lapse= {}" ,timeLapse);
            api.setResponse_time(timeLapse);
//            api.setNumTest(+1);

            if (response ==0){
                api.setStatus("sin replicas funcionales");
                apiRepository.save(api);
                logger.info("application_Id = {}, api_Id = {}, response = {}",
                        api.getApplicationId(), api.getApi_id(), api.getStatus());
                logger.info("respuesta del Api exitosa");
            }
                api.setStatus(response+"%");
                apiRepository.save(api);
                logger.info("{}",api.getStatus());
                logger.info("application_Id = {}, api_Id = {}, status = {}, ",
                        api.getApplicationId(), api.getApi_id(), api.getStatus());
                logger.info("respuesta del Api exitosa");

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
            String baseUrl = loadBalancer.getIpServer();
            String Json = loadBalancer.getPort();

            try{
                double firstDate = System.currentTimeMillis();
                ResponseEntity<Object> response = loadBalancerCurl.testLoadBalancer(baseUrl,Json);
                double timeLapse = System.currentTimeMillis() - firstDate;
                logger.info("time lapse= {}" ,timeLapse);

                if (response.getStatusCode().is2xxSuccessful()){

                    loadBalancer.setStatus(response.getStatusCode().toString());
                    loadBalancerRepository.save(loadBalancer);
                    logger.info("{}",loadBalancer.getStatus());
                    logger.info("application_Id = {}, load_balancer_id = {}, status = {}, ",
                            loadBalancer.getApplicationId(), loadBalancer.getVserver_id(),
                            loadBalancer.getStatus());
                    logger.info("respuesta del F5 exitosa");
                }
                else {
                    loadBalancer.setStatus(response.getStatusCode().toString());
                    loadBalancerRepository.save(loadBalancer);
                    logger.info("{}",loadBalancer);
                    logger.info("application_Id = {}, load_balancer_id = {}, status = {}, ",
                            loadBalancer.getApplicationId(), loadBalancer.getVserver_id(),
                            loadBalancer.getStatus());
                    logger.info("error al recibir respuesta");
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
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

            String method = servicio.getMethod();
            String baseUrl = servicio.getTestUrl();
            String Json = servicio.getJson();

            try {
                double firstDate = System.currentTimeMillis();
                ResponseEntity<Object> response = serviceCurl.testService(baseUrl,method,Json);
                double timeLapse = System.currentTimeMillis() - firstDate;
                logger.info("time lapse= {}" ,timeLapse);

                if (response.getStatusCode().isError()){

                    servicio.setStatus(response.getStatusCode().toString());
                    serviceRepository.save(servicio);
                    logger.info("{}",servicio);
                    logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
                            servicio.getApplicationId(), servicio.getService_id(), servicio.getStatus());
                    logger.info("error al recibir respuesta");
                }
                else {
                    servicio.setStatus(response.getStatusCode().toString());
                    serviceRepository.save(servicio);
                    logger.info("{}",servicio.getStatus());
                    logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
                            servicio.getApplicationId(), servicio.getService_id(), servicio.getStatus());
                    logger.info("respuesta del servicio exitosa");
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
