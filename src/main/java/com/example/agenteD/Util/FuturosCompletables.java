package com.example.agenteD.Util;

import com.example.agenteD.Entity.*;
import com.example.agenteD.Repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class FuturosCompletables implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(FuturosCompletables.class);

    @Autowired
    GenericStatement genericStatement;
    @Autowired
    ApplicationRepository applicationRepository;

    @Value("${monitor.aplication.name}")
    private String applicationNombre;

    public void run(String... args) throws Exception {

            CompletableFuture<List<Api>> apiResponse = genericStatement.apiCompletableFuture();

       /* CompletableFuture<List<Application>> applicationResponse = genericStatement.applicationCompletableFuture();
        CompletableFuture<List<Integration>> integrationResponse = genericStatement.integrationCompletableFuture();
        CompletableFuture<List<LoadBalancer>> loadBalancerResponse = genericStatement.loadBalancerCompletableFuture();
        CompletableFuture<List<Persistence>> persistenceResponse = genericStatement.persistenceCompletableFuture();
        CompletableFuture<List<Servicios>> serviceResponse = genericStatement.serviceCompletableFuture();


            CompletableFuture.allOf(apiResponse, applicationResponse, integrationResponse,
                    loadBalancerResponse, persistenceResponse, serviceResponse).join();*/

       /* Stream<String> applicationStream = Pattern.compile(",").splitAsStream(applicationNombre);
        applicationStream.map(x -> x.substring(0, x.length())).forEach(x -> {
            List<Application> applicationList = applicationRepository.findByApplicationName(x);
            applicationList.forEach(y -> {
                logger.info("{}", y.getApplication_id());
            });
        });*/


        String[] aplications = applicationNombre.split(",");
        int numApplication=0;
        for(String app : aplications)
        {
            Optional<Application> optId=applicationRepository.findByApplicationName(app);
            if(optId.isEmpty())
            {


                logger.error("la aplicación "+app +"  No esta defnida en la BD");
                continue;

            }
            else
            {

                Application application=optId.get();
                logger.info("Encontrada aplicación {}", app);
                logger.info("Id de la aplicación {}", application.getApplication_id());
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


