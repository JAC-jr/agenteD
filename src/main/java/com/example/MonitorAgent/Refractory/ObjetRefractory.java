package com.example.MonitorAgent.Refractory;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.SubProcess.SubProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ObjetRefractory {

    Logger logger = LoggerFactory.getLogger(ObjetRefractory.class);
    @Autowired
    SubProcess subProcess;

    @Async("ApiThreadPoolTaskExecutor")
   public void apiObjetResponse(Application application){

       while (true){
           try {
               List<Api> objectResponse = subProcess.apiSubProcessCompletableFuture(application).get();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } catch (ExecutionException e) {
               throw new RuntimeException(e);
           }
       }
   }


    @Async("IntegrationThreadPoolTaskExecutor")
    public void integrationObjetResponse(Application application){

        while (true){
            try {
                List<Integration> objectResponse = subProcess.integrationSubProcessCompletableFuture(application).get();
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
    public void objetResponse(com.example.MonitorAgent.Entity.Service service){

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
