package com.example.MonitorAgent.InterrogationMethods.IntegrationMethod;

import com.example.MonitorAgent.Entity.Integration;
import com.example.MonitorAgent.Entity.IntegrationRegistry;
import com.example.MonitorAgent.Repository.IntegrationRegistryRepository;
import com.example.MonitorAgent.Repository.IntegrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class IntegrationConfirmAndSave {
    @Autowired IntegrationRepository integrationRepository;
    @Autowired IntegrationRegistryRepository integrationRegistryRepository;
    Logger logger = LoggerFactory.getLogger(IntegrationConfirmAndSave.class);

    public void confirmAndSaveIntegration(LocalDateTime testTime, ResponseEntity<Object> response, Integration integration){

        if(Objects.equals(integration.getStatus(), response.getStatusCode().toString())){
            logger.info("Canal {} sin cambio",integration.getDescription());
        }
        else{
            logger.info("cambio de estado");
            saveNewIntegrationRegistry(integration);
            integration.setStatus(response.getStatusCode().toString());
        }
        integration.setLastTestDate(testTime);
        integration.setNumTest(integration.getNumTest() + 1);
        integrationRepository.save(integration);

    }

    public void saveNewIntegrationRegistry(Integration integration){
        IntegrationRegistry newRegistry = new IntegrationRegistry();
        newRegistry.setIntegration_id(integration.getIntegration_id());
        newRegistry.setConsecutiveSuccessfulTest(integration.getConsecutiveSuccessfulTest());
        newRegistry.setConsecutiveFailedTest(integration.getConsecutiveFailedTest());
        newRegistry.setLastTestDate(integration.getLastTestDate());
        newRegistry.setStatus(integration.getStatus());
        newRegistry.setApplicationId(integration.getApplicationId());
        newRegistry.setDescription(integration.getDescription());
        newRegistry.setResponse_time(integration.getResponse_time());
        newRegistry.setHistSuccessfulTest(integration.getHistorySuccessfulTest());
        newRegistry.setHistFailedTest(integration.getHistoryFailedTest());
        integrationRegistryRepository.save(newRegistry);
    }
}
