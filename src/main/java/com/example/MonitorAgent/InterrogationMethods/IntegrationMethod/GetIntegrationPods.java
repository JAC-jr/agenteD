package com.example.MonitorAgent.InterrogationMethods.IntegrationMethod;

import com.example.MonitorAgent.InterrogationMethods.LoadBalancerMethod.F5ResponseModel;
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
public class GetIntegrationPods {
    Logger logger = LoggerFactory.getLogger(GetIntegrationPods.class);
    @Autowired RestTemplate restTemplate;
    public ResponseEntity<Object> integrationKubeGet(String baseUrl, String Json) throws URISyntaxException {

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        JSONObject body = new JSONObject(Json);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(body.toString(), headers);
        ResponseEntity<Object> response = null;

        try {
            response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Object.class);
            logger.info("test exitoso de url: " + baseUrl);
            logger.info("status code = " + response.getStatusCode());

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error haciendo test de url: {} error {}", baseUrl, ex.getMessage());
            response = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
            return response;
        }
        return response;
    }
}
