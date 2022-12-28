package com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod;

import com.example.MonitorAgent.Entity.LoadBalancer;
import com.example.MonitorAgent.Repository.LoadBalancerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConfirmAndSave {
    @Autowired LoadBalancerRepository loadBalancerRepository;
    Logger logger = LoggerFactory.getLogger(ConfirmAndSave.class);

    public void confirmAndSaveF5(LocalDateTime testTime, ResponseEntity<F5ResponseModel> response, LoadBalancer loadBalancer){

//        LoadBalancer prev_vServer = loadBalancerRepository.findByVserverIdAndActualState(loadBalancer.getVserverId(),true);

        if(Objects.equals(loadBalancer.getStatus(), response.getStatusCode().toString())){
            logger.info("mismo estado");
            loadBalancer.setStatus(response.getStatusCode().toString());
            loadBalancer.setLastTestDate(testTime);
            loadBalancer.setNumTest(loadBalancer.getNumTest() + 1);
            loadBalancerRepository.save(loadBalancer);
        }
        else{
            logger.info("cambio de estado");
//            loadBalancer.setActualState(false);
//            loadBalancerRepository.save(loadBalancer);
            saveNewRegistryF5(testTime, response, loadBalancer);
        }

    }

    public void saveNewRegistryF5(LocalDateTime testTime, ResponseEntity<F5ResponseModel> response, LoadBalancer loadBalancer){
        LoadBalancer newRegistry = new LoadBalancer();
        newRegistry.setSuccessfulConsecutiveTest(loadBalancer.getSuccessfulConsecutiveTest());
        newRegistry.setFailedConsecutiveTest(loadBalancer.getFailedConsecutiveTest());
        newRegistry.setNumTest(loadBalancer.getNumTest()+1);
        newRegistry.setDescription(loadBalancer.getDescription());
        newRegistry.setActualState(false);
        newRegistry.setLastTestDate(testTime);
        newRegistry.setStatus(response.getStatusCode().toString());
        newRegistry.setNumTest(0);
        loadBalancerRepository.save(newRegistry);
    }
}
