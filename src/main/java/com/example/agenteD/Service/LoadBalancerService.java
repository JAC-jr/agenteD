package com.example.agenteD.Service;

import com.example.agenteD.Entity.LoadBalancer;
import com.example.agenteD.Repository.LoadBalancerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Service
public class LoadBalancerService {
    Logger logger = LoggerFactory.getLogger(LoadBalancerService.class);

}
