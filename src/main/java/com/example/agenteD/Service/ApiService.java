package com.example.agenteD.Service;

import com.example.agenteD.Entity.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@Service
public class ApiService {

    Logger logger = LoggerFactory.getLogger(ApiService.class);

   public void subProcess(List<Api> result){
       result.forEach(p -> {
       });

   }
}
