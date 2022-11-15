package com.example.agenteD.Util;

import com.example.agenteD.Entity.Api;
import com.example.agenteD.Repository.ApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SubProcess {
    @Autowired
    ApiRepository apiRepository;
    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<Long> subProcessCompletableFuture(Api api) throws InterruptedException{
        logger.info("Looking up api_id = {}", api.getApi_id());
        Long result = api.getTestInterv();
        //Long result = apiRepository.getReferenceById(api).getTestInterv();
        logger.info("{}",result);
        Thread.sleep(result);

    return CompletableFuture.completedFuture(result);
    }

}
