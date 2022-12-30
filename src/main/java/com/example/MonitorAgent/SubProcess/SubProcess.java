package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.GetApiPods;
import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.F5ResponseModel;
import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.LoadBalancerCurl;

import com.example.MonitorAgent.InterrogationMethods.PersistencesMethod.GetConnectionData;
import com.example.MonitorAgent.InterrogationMethods.ServiceMethod.GetServicesPods;
import com.example.MonitorAgent.InterrogationMethods.ServiceMethod.ResponseStatus;
import com.example.MonitorAgent.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired ApiRepository apiRepository;
    @Autowired IntegrationRepository integrationRepository;
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired PersistenceRepository persistenceRepository;
    @Autowired ServiceRepository serviceRepository;
    @Autowired GetServicesPods getServicesPods;
    @Autowired LoadBalancerCurl loadBalancerCurl;
    @Autowired
    GetApiPods getApiPods;
    @Autowired
    GetConnectionData getConnectionData;

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
            api.setNumTest(+1);
            api.setLastTestDate(LocalDateTime.now());

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
            String baseUrl = loadBalancer.getDescription();
            String Json = loadBalancer.getStatus();

            try{
                long firstDate = System.currentTimeMillis();
                ResponseEntity<F5ResponseModel> response = loadBalancerCurl.testLoadBalancer(baseUrl,Json);
                long timeLapse = System.currentTimeMillis() - firstDate;
                logger.info("time lapse= {}" ,timeLapse);
                loadBalancer.setResponse_time(timeLapse);
                loadBalancer.setNumTest(+1);
                loadBalancer.setLastTestDate(LocalDateTime.now());

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

            String UrlServices = servicio.getTestUrl();
            String nameSpace = servicio.getNameSpace();
            String labelApp = servicio.getLabelApp();
            Integer serviceId = servicio.getServiceId();

            double firstDate = System.currentTimeMillis();
            double response = getServicesPods.apiKubeGet(UrlServices, nameSpace, labelApp, serviceId);
            getServicesPods.apiKubeGet(UrlServices, nameSpace, labelApp, serviceId);
            double timeLapse = System.currentTimeMillis() - firstDate;
            logger.info("time lapse= {}" ,timeLapse);

            if (response ==0){

                servicio.setStatus("sin replicas funcionales");
                serviceRepository.save(servicio);
                logger.info("{}",servicio);
                logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
                        servicio.getApplicationId(), servicio.getServiceId(), servicio.getStatus());
                logger.info("error al recibir respuesta");
            }
            else {
                servicio.setStatus(response+"%");
                serviceRepository.save(servicio);
                logger.info("{}",servicio.getStatus());
                logger.info("application_Id = {}, Service_Id = {}, status = {}, ",
                        servicio.getApplicationId(), servicio.getServiceId(), servicio.getStatus());
                logger.info("respuesta del servicio exitosa");

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

            String UrlPersistence = persistence.getUrl();
            String UserName = persistence.getUserName();
            String Password = persistence.getPassword();
            String SQL = persistence.getSqlSentence();
            String DBType = persistence.getDbType();

            try {

           String response = getConnectionData.getDATA(UrlPersistence, UserName, Password, SQL, DBType);

            persistence.setStatus(response);
            persistenceRepository.save(persistence);

            logger.info("{}",response);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            logger.info("{}",UrlPersistence, UserName, Password, SQL, DBType);


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
