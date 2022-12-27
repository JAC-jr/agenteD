package com.example.MonitorAgent.InterrogationMethods.ServiceMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ResponseStatus {

    Logger logger = LoggerFactory.getLogger(ResponseStatus.class);


    public String checkstatus(ResponseEntity<Object>response) {

        ObjectMapper mapperIn = new ObjectMapper();
        String responseString = null;

        logger.info("response = {}", response.toString());
        logger.info("response body = {}", Objects.requireNonNull(response.getBody()).toString());

        try {
            String responseJSON = mapperIn.writeValueAsString(response.getBody());
            JSONObject responseObject = new JSONObject(responseJSON);

            responseString = responseObject.getString("status");

            logger.info("response status = {}", responseString);

            if (responseString.equals("UP")) {
                responseString = "OK";
            } else {
                responseString = "BAD";
            }
            logger.info("response status = {}", responseString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseString;
    }

}

