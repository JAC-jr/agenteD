package com.example.MonitorAgent.NextStep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class NextStep {

    Logger logger = LoggerFactory.getLogger(NextStep.class);
    @Autowired
    RestTemplate restTemplate;
    public HttpEntity<Object> testUrl (String baseUrl) throws URISyntaxException {
        //http://", serviceName,":", port

        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        //headers.set("X-COM-LOCATION", "USA");

        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        try
        {
            restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            //Assert.fail();
            logger.debug("test exitoso de url: {}",baseUrl);
        }
        catch(Exception ex)
        {
            logger.error("error haciendo test de url: {} error {}",baseUrl,ex.getMessage());

        }
        return requestEntity;
    }
}