package com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod;

import com.example.MonitorAgent.Entity.LoadBalancer;
import com.example.MonitorAgent.Entity.LoadBalancerRegistry;
import com.example.MonitorAgent.Repository.LoadBalancerRepository;
import com.example.MonitorAgent.Repository.LoadBalancerRegistryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConfirmAndSaveLoadBalancer {
    @Autowired LoadBalancerRepository loadBalancerRepository;
    @Autowired LoadBalancerRegistryRepository loadBalancerRegistryRepository;
    Logger logger = LoggerFactory.getLogger(ConfirmAndSaveLoadBalancer.class);

    public void confirmAndSaveF5(LocalDateTime testTime, ResponseEntity<F5ResponseModel> response, LoadBalancer loadBalancer){

        if(Objects.equals(loadBalancer.getStatus(), response.getStatusCode().toString())){
            logger.info("loadBalancer {} sin cambio",loadBalancer.getDescription());
        }
        else{
            logger.info("cambio de estado");
            saveNewRegistryF5(loadBalancer);
            loadBalancer.setStatus(response.getStatusCode().toString());
        }
        loadBalancer.setLastTestDate(testTime);
        loadBalancer.setNumTest(loadBalancer.getNumTest() + 1);
        loadBalancerRepository.save(loadBalancer);

    }

    public void saveNewRegistryF5(LoadBalancer loadBalancer){
        LoadBalancerRegistry newRegistry = new LoadBalancerRegistry();
        newRegistry.setVserverId(loadBalancer.getVserverId());
        newRegistry.setSuccessfulConsecutiveTest(loadBalancer.getSuccessfulConsecutiveTest());
        newRegistry.setFailedConsecutiveTest(loadBalancer.getFailedConsecutiveTest());
        newRegistry.setDescription(loadBalancer.getDescription());
        newRegistry.setLastTestDate(loadBalancer.getLastTestDate());
        newRegistry.setStatus(loadBalancer.getStatus());
        newRegistry.setApplicationId(loadBalancer.getApplicationId());
        newRegistry.setUrlServer(loadBalancer.getUrlServer());
        newRegistry.setResponse_time(loadBalancer.getResponse_time());
        newRegistry.setHistorySuccessfulTest(loadBalancer.getHistorySuccessfulTest());
        newRegistry.setHistoryFailedTest(loadBalancer.getHistoryFailedTest());
        loadBalancerRegistryRepository.save(newRegistry);
    }
}
