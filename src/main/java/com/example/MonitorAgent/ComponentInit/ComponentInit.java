package com.example.MonitorAgent.ComponentInit;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.Refractory.EntityRefractory;
import com.example.MonitorAgent.Refractory.ObjetRefractory;
import com.example.MonitorAgent.Repository.ApplicationRepository;
import com.example.MonitorAgent.SubProcess.SubProcess;
import com.example.MonitorAgent.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.persistence.Id;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class ComponentInit implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(ComponentInit.class);
    @Autowired
    EntityRefractory entityRefractory;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    SubProcess subProcess;
    @Autowired
    TestService testService;
    @Autowired
    ObjetRefractory objetRefractory;

    @Value("${monitor.application}")
    private String applicationName;

    public void run(String... args) throws Exception {

        String[] applications = applicationName.split(",");
        int numApplication=0;
        for(String app : applications)
        {
            Optional<Application> optId=applicationRepository.getIdByApplicationName(app);
            if(optId.isEmpty())
            {
                logger.error("la application "+app +"  No esta definida en la BD");
                continue;
            }
            else
            {
                Application application = optId.get();
                Integer applicationId = application.getApplication_id();
                logger.info("Encontrada aplicación {}", app);
                logger.info("id = {}", application.getApplication_id());
                objetRefractory.apiObjetResponse(application);
                objetRefractory.integrationObjetResponse(application);
                //objetRefractory.objetResponse(optId.get());
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


