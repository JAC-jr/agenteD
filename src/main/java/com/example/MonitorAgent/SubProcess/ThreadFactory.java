package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class ThreadFactory {

    @Autowired
    ProcessThreads processThreads;

    @Async("ApiThreadPoolTaskExecutor")
    public void apiObjetResponse(Application application){

       while (true){
           try {
               List<Api> objectResponse = processThreads.apiSubProcessCompletableFuture(application).get();
           } catch (InterruptedException | ExecutionException e) {
               throw new RuntimeException(e);
           }
       }
   }

    @Async("IntegrationThreadPoolTaskExecutor")
    public void integrationObjetResponse(Application application){

        while (true){
            try {
                List<Integration> objectResponse = processThreads.integrationSubProcessCompletableFuture(application).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("LoadBalancerThreadPoolTaskExecutor")
    public void loadBalancerObjetResponse(Application application){

        while (true){
            try {
                List<LoadBalancer> objectResponse = processThreads.loadBalancerSubProcessCompletableFuture(application).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("PersistenceThreadPoolTaskExecutor")
    public void persistenceObjetResponse(Application application){

        while (true){
            try {
                List<Persistence> objectResponse = processThreads.persistenceSubProcessCompletableFuture(application).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("ServiceThreadPoolTaskExecutor")
    public void serviceObjetResponse(Application application){

        while (true){
            try {
                List<Servicio> objectResponse = processThreads.serviceSubProcessCompletableFuture(application).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
