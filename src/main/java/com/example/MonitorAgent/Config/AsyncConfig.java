package com.example.MonitorAgent.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "ApiThreadPoolTaskExecutor")
    public Executor apiThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("apiThread--");
        executor.initialize();
        return executor;
    }

    @Bean(name = "ApplicationThreadPoolTaskExecutor")
    public Executor applicationThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("applicationThread");
        executor.initialize();
        return executor;
    }

    @Bean(name = "IntegrationThreadPoolTaskExecutor")
    public Executor integrationThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("IntegrationThread--");
        executor.initialize();
        return executor;
    }

    @Bean(name = "LoadBalancerThreadPoolTaskExecutor")
    public Executor loadBalancerThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("LoadBalancerThread--");
        executor.initialize();
        return executor;
    }

    @Bean(name = "PersistenceThreadPoolTaskExecutor")
    public Executor persistenceThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("PersistenceThread--");
        executor.initialize();
        return executor;
    }

    @Bean(name = "ServiceThreadPoolTaskExecutor")
    public Executor serviceThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("ServiceThread--");
        executor.initialize();
        return executor;
    }
}
//    @Bean(name = "ApiReplicaThreadPoolTaskExecutor")
//    public Executor apiReplicaThreadPoolTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(6);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("ApiReplicaThread--");
//        executor.initialize();
//        return executor;
//    }

