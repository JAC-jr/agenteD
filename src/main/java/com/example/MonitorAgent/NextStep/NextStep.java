package com.example.MonitorAgent.NextStep;

import com.example.MonitorAgent.ResponseModel.ResponseBase;
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
    public HttpEntity<ResponseBase> testUrl (String baseUrl) throws URISyntaxException {
        //http://", serviceName,":", port

        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ResponseBase> requestEntity = new HttpEntity<>(headers);
        try
        {
             requestEntity =restTemplate.exchange(
                    uri, HttpMethod.GET, requestEntity, ResponseBase.class);
            logger.debug("test exitoso de url: {}",baseUrl);
        }
        catch(Exception ex)
        {
            logger.error("error haciendo test de url: {} error {}",baseUrl,ex.getMessage());
        }
        return requestEntity;
    }
}