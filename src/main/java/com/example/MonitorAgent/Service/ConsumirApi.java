package com.example.MonitorAgent.Service;


import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;

@Service
public class ConsumirApi {

    @Autowired private RestTemplate restTemplate;
    String URL = "";

    public void givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode() throws ClientProtocolException, IOException {
        HttpClient client = HttpClientBuilder
                .create()
                .build();
        HttpResponse response = client.execute(new HttpGet(URL));
        int statusCode = response.getStatusLine().getStatusCode();


        System.out.println(response.getStatusLine().getStatusCode());

    }

}
