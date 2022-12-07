package com.example.MonitorAgent.Threadfractory;

import com.example.MonitorAgent.Entity.*;
import com.example.MonitorAgent.NextStep.ApiCurl;
import com.example.MonitorAgent.SubProcess.SubProcess;
import io.kubernetes.client.openapi.models.V1Pod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class ThreadFactory {

    Logger logger = LoggerFactory.getLogger(ThreadFactory.class);
    @Autowired
    SubProcess subProcess;
    @Autowired
    ApiCurl apiCurl;
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
    public void loadBalancerObjetResponse(Application application){

        while (true){
            try {
                List<LoadBalancer> objectResponse = subProcess.loadBalancerSubProcessCompletableFuture(application).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("PersistenceThreadPoolTaskExecutor")
    public void persistenceObjetResponse(Application application){

        while (true){
            try {
                List<Persistence> objectResponse = subProcess.persistenceSubProcessCompletableFuture(application).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Async("ServiceThreadPoolTaskExecutor")
    public void serviceObjetResponse(Application application){

        while (true){
            try {
                List<Servicio> objectResponse = subProcess.serviceSubProcessCompletableFuture(application).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    @Async("ApiReplicaThreadPoolTaskExecutor")
//    public void apiReplicaObjetResponse(V1Pod item, String baseUrl){
//
//        while (true){
//            try {
//                ResponseEntity<Object> objectResponse = apiCurl.testApi(item, baseUrl).get();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            } catch (URISyntaxException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
