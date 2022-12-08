package com.example.MonitorAgent.NextStep;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ServiceCurl {

    Logger logger = LoggerFactory.getLogger(ServiceCurl.class);
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity<Object> testService (String baseUrl,String method,String Json) throws URISyntaxException {

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        JSONObject body = new JSONObject(Json);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(body.toString(),headers);
        ResponseEntity<Object> response=null;
        try
        {

            response = restTemplate.exchange(uri, HttpMethod.valueOf(method), requestEntity, Object.class);
            logger.debug("test exitoso de url: {}",baseUrl);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("error haciendo test de url: {} error {}",baseUrl,ex.getMessage());
        }
        return response;
    }
}