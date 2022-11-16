package com.example.MonitorAgent;

import com.example.MonitorAgent.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    ApplicationRepository applicationRepository;

   public void getById (Integer y) {
       applicationRepository.findById(y);
   }
}
