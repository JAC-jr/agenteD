package com.example.agenteD.Service;

import com.example.agenteD.Entity.Application;
import com.example.agenteD.Util.SubProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ApplicationService {
    Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    SubProcess subProcess;

    @Async("ApplicationThreadPoolTaskExecutor")
    public void objetResponse(Application application){

        while (true){
            try {
                Long objectResponse = subProcess.applicationSubProcessCompletableFuture(application).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
