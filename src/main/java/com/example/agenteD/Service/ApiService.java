package com.example.agenteD.Service;

import com.example.agenteD.Entity.Api;
import com.example.agenteD.Util.SubProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ApiService {

    Logger logger = LoggerFactory.getLogger(ApiService.class);
    @Autowired
    SubProcess subProcess;

    @Async("SubProcessThreadPoolTaskExecutor")
   public void objetResponse(Api api){
       while (true){
           try {
               Long objectResponse = subProcess.subProcessCompletableFuture(api).get();
               /*Integer applicationId = api.getApplicationId();
               String serviceName = api.getServiceName();
               String nameSpace = api.getNameSpace();
               logger.info("data: applicationId = {}, serviceName = {}, nameSpace = {}",
                       applicationId, serviceName, nameSpace);*/
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } catch (ExecutionException e) {
               throw new RuntimeException(e);
           }
       }
   }
}
