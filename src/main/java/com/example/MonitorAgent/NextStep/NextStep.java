package com.example.MonitorAgent.NextStep;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class NextStep {

    Logger logger = LoggerFactory.getLogger(NextStep.class);
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity<Object> testUrl (String baseUrl, String Http_metodo) throws URISyntaxException {

        URI uri = new URI(baseUrl);



        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = null;

        HttpMethod method = HttpMethod.valueOf(Http_metodo);


        try
        {

        response = restTemplate.exchange(uri, method, requestEntity, Object.class);



            logger.info("getStatusCodeValue: " + new ObjectMapper().writeValueAsString(response.getStatusCodeValue()));

            logger.info("Aqui viene la informacion baseUrl: " + new ObjectMapper().writeValueAsString(response));
            logger.info("Aqui viene la informacion response.getHeaders(): " + new ObjectMapper().writeValueAsString(response.getHeaders()));
            logger.info("Aqui viene la informacion response.getBody(): " + new ObjectMapper().writeValueAsString(response.getBody()));


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("error haciendo test de url: {} error {}",baseUrl,ex.getMessage());
        }
        return response;
    }
}