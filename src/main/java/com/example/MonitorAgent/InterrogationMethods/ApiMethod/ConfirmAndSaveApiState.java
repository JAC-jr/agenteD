package com.example.MonitorAgent.InterrogationMethods.ApiMethod;

import com.example.MonitorAgent.Entity.Api;
import com.example.MonitorAgent.Entity.ApiRegistry;
import com.example.MonitorAgent.Repository.ApiRegistryRepository;
import com.example.MonitorAgent.Repository.ApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConfirmAndSaveApiState {
    @Autowired ApiRepository apiRepository;
    @Autowired ApiRegistryRepository apiRegistryRepository;
    Logger logger = LoggerFactory.getLogger(ConfirmAndSaveApiState.class);
    public void confirmAndSaveApi(Api api, String status, LocalDateTime tesTime, double response){

        if(Objects.equals(api.getStatus(), status)) {
            logger.info("Api " + api.getLabel_app());
            logger.info("mismo estado");
            api.setHealth(response+"%");
        }
        else{
            logger.info("cambio de estado");
            saveNewRegistryApi(api);
            api.setStatus(status);
            api.setHealth(response+"%");
        }
        confirmStatus(api, status);
        api.setLastTestDate(tesTime);
        api.setNumTest(api.getNumTest() + 1);
        apiRepository.save(api);

    }

    public void saveNewRegistryApi(Api api){
        ApiRegistry newRegistry = new ApiRegistry();
        newRegistry.setApi_id(api.getApi_id());
        newRegistry.setConsecutiveSuccessfulTest(api.getConsecutiveSuccessfulTest());
        newRegistry.setConsecutiveFailedTest(api.getConsecutiveFailedTest());
        newRegistry.setHealth(api.getHealth());
        newRegistry.setLabel_app(api.getLabel_app());
        newRegistry.setNameSpace(api.getNameSpace());
        newRegistry.setLastTestDate(api.getLastTestDate());
        newRegistry.setStatus(api.getStatus());
        newRegistry.setApplicationId(api.getApplicationId());
        newRegistry.setResponse_time(api.getResponse_time());
        newRegistry.setHistSuccessfulTest(api.getHistSuccessfulTest());
        newRegistry.setHistFailedTest(api.getHistFailedTest());
        apiRegistryRepository.save(newRegistry);
    }
    public void confirmStatus(Api api, String status) {
        if (Objects.equals(status, "good")){
            api.setConsecutiveSuccessfulTest(api.getConsecutiveSuccessfulTest()+1);
            api.setHistSuccessfulTest(api.getHistSuccessfulTest()+1);
            api.setConsecutiveFailedTest(0);
        }
        else if (Objects.equals(status, "healthy")){
            api.setConsecutiveSuccessfulTest(api.getConsecutiveSuccessfulTest()+1);
            api.setHistSuccessfulTest(api.getHistSuccessfulTest()+1);
            api.setConsecutiveFailedTest(0);
        }
        else if (Objects.equals(status, "bad")){
            api.setHistFailedTest(api.getHistFailedTest()+1);
            api.setConsecutiveFailedTest(api.getConsecutiveFailedTest()+1);
            api.setConsecutiveSuccessfulTest(0);
        }
        else if (Objects.equals(status, "sin replicas funcionales")) {
            api.setHistFailedTest(api.getHistFailedTest()+1);
            api.setConsecutiveFailedTest(api.getConsecutiveFailedTest()+1);
            api.setConsecutiveSuccessfulTest(0);
        }
    }

}
