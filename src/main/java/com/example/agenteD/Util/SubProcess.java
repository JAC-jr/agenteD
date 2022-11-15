package com.example.agenteD.Util;

import com.example.agenteD.Entity.Api;
import com.example.agenteD.Entity.Application;
import com.example.agenteD.Repository.ApiRepository;
import com.example.agenteD.Repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired
    ApplicationRepository applicationRepository;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<Long> apiSubProcessCompletableFuture(Api api) throws InterruptedException{
        Long result = api.getTestInterv();
        logger.info("api_id = {}, testInterv = {}",api.getApi_id(), result);
        Thread.sleep(result);

    return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<Long> applicationSubProcessCompletableFuture(Application application) throws InterruptedException{
        Long result = application.getTestInterv();
        logger.info("application_id = {}, testInterv = {}",application.getApplication_id(), result);
        Thread.sleep(result);

        return CompletableFuture.completedFuture(result);
    }

}
