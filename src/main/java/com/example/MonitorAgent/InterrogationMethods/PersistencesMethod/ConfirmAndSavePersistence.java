package com.example.MonitorAgent.InterrogationMethods.PersistencesMethod;

import com.example.MonitorAgent.Entity.Persistence;
import com.example.MonitorAgent.Entity.PersistenceRegistry;
import com.example.MonitorAgent.Repository.PersistenceRegistryRepository;
import com.example.MonitorAgent.Repository.PersistenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConfirmAndSavePersistence {
    @Autowired PersistenceRepository persistenceRepository;
    @Autowired PersistenceRegistryRepository persistenceRegistryRepository;
    Logger logger = LoggerFactory.getLogger(ConfirmAndSavePersistence.class);

    public void confirmAndSavePersistence(LocalDateTime testTime, String response, Persistence persistence){

        if(Objects.equals(persistence.getStatus(), response)){
            logger.info("persistence {} sin cambio",persistence.getDescription());
        }
        else{
            logger.info("cambio de estado");
            saveNewRegistryPersistence(persistence);
            persistence.setStatus(response);
        }
        persistence.setLastTestDate(testTime);
        persistence.setNumTest(persistence.getNumTest() + 1);
        persistenceRepository.save(persistence);

    }

    public void saveNewRegistryPersistence(Persistence persistence){
        PersistenceRegistry newRegistry = new PersistenceRegistry();
        newRegistry.setDb_id(persistence.getDb_id());
        newRegistry.setDbName(persistence.getDbName());
        newRegistry.setConsecutiveSuccessfulTest(persistence.getConsecutiveSuccessfulTest());
        newRegistry.setConsecutiveFailedTest(persistence.getConsecutiveFailedTest());
        newRegistry.setDescription(persistence.getDescription());
        newRegistry.setLastTestDate(persistence.getLastTestDate());
        newRegistry.setStatus(persistence.getStatus());
        newRegistry.setApplicationId(persistence.getApplicationId());
        newRegistry.setResponse_time(persistence.getResponse_time());
        newRegistry.setHistSuccessfulTest(persistence.getHistorySuccessfulTest());
        newRegistry.setHistFailedTest(persistence.getHistoryFailedTest());
        newRegistry.setDbType(persistence.getDbType());
        persistenceRegistryRepository.save(newRegistry);
    }
}
