package com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod;

import com.example.MonitorAgent.Entity.LoadBalancer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class LoadBalancerCurl {

    Logger logger = LoggerFactory.getLogger(LoadBalancerCurl.class);
    @Autowired RestTemplate restTemplate;

    public ResponseEntity<F5ResponseModel> testLoadBalancer (String baseUrl, String Json, LoadBalancer loadBalancer) throws URISyntaxException, IOException {

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        JSONObject body = new JSONObject(Json);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(body.toString(),headers);
        ResponseEntity<F5ResponseModel> response = null;

        try
        {
            response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, F5ResponseModel.class);
            logger.info("test exitoso de url: " + baseUrl);
            logger.info("status code = " + response.getStatusCode());

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("error haciendo test de url: {} error {}",baseUrl,ex.getMessage());
            response = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
//            return response;
        }
        return response;
    }
}
