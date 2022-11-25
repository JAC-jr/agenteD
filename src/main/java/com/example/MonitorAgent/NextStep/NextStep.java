package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.ResponseModel.ResponseBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Objects;

@Service
public class NextStep {

    Logger logger = LoggerFactory.getLogger(NextStep.class);
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity<Object> testUrl (String baseUrl) throws URISyntaxException {

        URI uri = new URI(baseUrl);

        //ResponseBase responseBase = null;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.OK);
        try
        {
//             requestEntity =restTemplate.exchange(
//                    uri, HttpMethod.HEAD, requestEntity, ResponseBase.class);
            logger.debug("test exitoso de url: {}",baseUrl);
        response = restTemplate.exchange(uri, HttpMethod.HEAD, requestEntity, Object.class);

           // LinkedHashMap<String, Object> respons = (LinkedHashMap<String, Object>) response;

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