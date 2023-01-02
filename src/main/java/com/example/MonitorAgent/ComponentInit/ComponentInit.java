package com.example.MonitorAgent.ComponentInit;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.SubProcess.ThreadFactory;
import com.example.MonitorAgent.Repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ComponentInit implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(ComponentInit.class);
    @Autowired ApplicationRepository applicationRepository;
    @Autowired ThreadFactory threadFactory;

    @Value("${monitor.application}")
    private String applicationName;

    public void run(String... args) throws Exception {

        String[] applications = applicationName.split(",");
        int numApplication=0;
        for(String app : applications)
        {
            Optional<Application> optId = applicationRepository.getIdByApplicationName(app);
            if(optId.isEmpty())
            {
                logger.error("la application "+ app +"  No esta definida en la BD");
                continue;
            }
            else
            {
                Application application = optId.get();
                Integer applicationId = application.getApplication_id();
                logger.info("Encontrada aplicación {}", app);
                logger.info("id = {}", application.getApplication_id());
//                threadFactory.apiObjetResponse(application);
//                threadFactory.loadBalancerObjetResponse(application);
//                threadFactory.serviceObjetResponse(application);
                threadFactory.persistenceObjetResponse(application);
                //threadFactory.integrationObjetResponse(application);
                numApplication++;
            }
        }
        if(numApplication == 0 )
        {
            logger.error("No existen aplicacione configuradas en la BD");
            System.exit(1);
        }
    }
}


