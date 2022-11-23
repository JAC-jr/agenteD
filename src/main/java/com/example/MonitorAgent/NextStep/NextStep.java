package com.example.MonitorAgent.NextStep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class NextStep {

    Logger logger = LoggerFactory.getLogger(NextStep.class);

    public String getCurl (String serviceName, String port){
        //http://", serviceName,":", port
        String[] commands = {"curl", "-k", "GET",
                "https://openweathermap.org/apihttps://openweathermap.org/apiv"};
        Process process;
        try {
            process = Runtime.getRuntime().exec(commands);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String line;
        String response = null ;

        while (true) {
            try {
                if (((line = reader.readLine()) == null)) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.lines();
        }
        return response;
    }
}
