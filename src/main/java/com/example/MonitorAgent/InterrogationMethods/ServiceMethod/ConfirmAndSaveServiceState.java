package com.example.MonitorAgent.InterrogationMethods.ServiceMethod;

import com.example.MonitorAgent.Entity.Servicio;
import com.example.MonitorAgent.Entity.ServicioRegistry;
import com.example.MonitorAgent.InterrogationMethods.ApiMethod.ConfirmAndSaveApiState;
import com.example.MonitorAgent.Repository.ServiceRegistryRepository;
import com.example.MonitorAgent.Repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConfirmAndSaveServiceState {
    @Autowired ServiceRepository serviceRepository;
    @Autowired ServiceRegistryRepository serviceRegistryRepository;
    Logger logger = LoggerFactory.getLogger(ConfirmAndSaveApiState.class);
    public void confirmAndSaveService(Servicio service, String status, LocalDateTime testTime, double response, long timelapse){

        if(Objects.equals(service.getStatus(), status)) {
            logger.info("Service " + service.getLabelApp());
            logger.info("mismo estado");
            service.setHealth(response+"%");
        }
        else{
            logger.info("cambio de estado");
            saveNewRegistryService(service);
            service.setStatus(status);
            service.setHealth(response+"%");
        }
        confirmStatus(service, status);
        service.setResponse_time(timelapse);
        service.setLastTestsDate(testTime);
        service.setNumTest(service.getNumTest() + 1);
        serviceRepository.save(service);

    }

    public void saveNewRegistryService(Servicio service){
        ServicioRegistry newRegistry = new ServicioRegistry();
        newRegistry.setService_id(service.getServiceId());
        newRegistry.setConsecutiveSuccessfulTest(service.getConsecutiveSuccessfulTest());
        newRegistry.setConsecutiveFailedTest(service.getConsecutiveFailedTest());
        newRegistry.setHealth(service.getHealth());
        newRegistry.setLabel_app(service.getLabelApp());
        newRegistry.setNameSpace(service.getNameSpace());
        newRegistry.setLastTestDate(service.getLastTestsDate());
        newRegistry.setStatus(service.getStatus());
        newRegistry.setApplicationId(service.getApplicationId());
        newRegistry.setResponse_time(service.getResponse_time());
        newRegistry.setHistSuccessfulTest(service.getHistSuccessfulTest());
        newRegistry.setHistFailedTest(service.getHistFailedTest());
        serviceRegistryRepository.save(newRegistry);
    }
    public void confirmStatus(Servicio service, String status) {
        if (Objects.equals(status, "good")){
            service.setConsecutiveSuccessfulTest(service.getConsecutiveSuccessfulTest()+1);
            service.setHistSuccessfulTest(service.getHistSuccessfulTest()+1);
            service.setConsecutiveFailedTest(0);
        }
        else if (Objects.equals(status, "healthy")){
            service.setConsecutiveSuccessfulTest(service.getConsecutiveSuccessfulTest()+1);
            service.setHistSuccessfulTest(service.getHistSuccessfulTest()+1);
            service.setConsecutiveFailedTest(0);
        }
        else if (Objects.equals(status, "bad")){
            service.setHistFailedTest(service.getHistFailedTest()+1);
            service.setConsecutiveFailedTest(service.getConsecutiveFailedTest()+1);
            service.setConsecutiveSuccessfulTest(0);
        }
        else if (Objects.equals(status, "sin replicas funcionales")) {
            service.setHistFailedTest(service.getHistFailedTest()+1);
            service.setConsecutiveFailedTest(service.getConsecutiveFailedTest()+1);
            service.setConsecutiveSuccessfulTest(0);
        }
    }
}






