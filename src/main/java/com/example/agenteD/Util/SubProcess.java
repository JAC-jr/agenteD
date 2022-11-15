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
    @Autowired ApiRepository apiRepository;
    @Autowired  ApplicationRepository applicationRepository;


    Logger logger = LoggerFactory.getLogger(SubProcess.class);
    
    public CompletableFuture<Long> subProcessCompletableFuture(Api api) throws InterruptedException{
        Long result = api.getTestInterv();
        logger.info("{}",result);
        Thread.sleep(result);

    return CompletableFuture.completedFuture(result);
    }

}
