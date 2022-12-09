package com.example.MonitorAgent.NextStep;

import io.kubernetes.client.openapi.models.V1Pod;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@Service
public class ApiCurl {
    Logger logger = LoggerFactory.getLogger(ApiCurl.class);
    @Autowired
    RestTemplate restTemplate;
//        @Async("ApiReplicaThreadPoolTaskExecutor")
//    public void apiReplicaObjetResponse(V1Pod item, String baseUrl){
//
//        while (true){
//            try {
//                ResponseEntity<Object> objectResponse = testApi(item, baseUrl).get();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            } catch (URISyntaxException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public ResponseEntity<Object> testApi (V1Pod item, String baseUrl) throws URISyntaxException {

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Object> response=null;

        try {
            logger.info("apiName: {}, podIp:{}",item.getMetadata().getName(),
                    item.getStatus().getPodIP());

            String apiName = item.getMetadata().getLabels().get("app");

            logger.info("{}", apiName);

            logger.info("curl http://" + item.getStatus().getPodIP()+ baseUrl);

            response = restTemplate.exchange(
                    "https://" + item.getStatus().getPodIP()+ baseUrl,
                    HttpMethod.GET, requestEntity, Object.class);

            logger.info("response = {}", response);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("error haciendo test de url: {} error {}",baseUrl,ex.getMessage());
        }
        return response;
    }
}