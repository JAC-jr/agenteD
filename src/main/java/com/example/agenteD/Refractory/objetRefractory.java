package com.example.agenteD.Refractory;

import com.example.agenteD.Entity.*;
import com.example.agenteD.SubProcess.SubProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class objetRefractory {

    Logger logger = LoggerFactory.getLogger(objetRefractory.class);
    @Autowired
    SubProcess subProcess;

    @Async("ApiThreadPoolTaskExecutor")
   public void objetResponse(Api api){

       while (true){
           try {
               Long objectResponse = subProcess.apiSubProcessCompletableFuture(api).get();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } catch (ExecutionException e) {
               throw new RuntimeException(e);
           }
       }
   }

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

    @Async("IntegrationThreadPoolTaskExecutor")
    public void objetResponse(Integration integration){

        while (true){
            try {
                Long objectResponse = subProcess.integrationSubProcessCompletableFuture(integration).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("LoadBalancerThreadPoolTaskExecutor")
    public void objetResponse(LoadBalancer loadBalancer){

        while (true){
            try {
                Long objectResponse = subProcess.loadBalancerSubProcessCompletableFuture(loadBalancer).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("PersistenceThreadPoolTaskExecutor")
    public void objetResponse(Persistence persistence){

        while (true){
            try {
                Long objectResponse = subProcess.persistenceSubProcessCompletableFuture(persistence).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("ServiceThreadPoolTaskExecutor")
    public void objetResponse(com.example.agenteD.Entity.Service service){

        while (true){
            try {
                Long objectResponse = subProcess.serviceSubProcessCompletableFuture(service).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
