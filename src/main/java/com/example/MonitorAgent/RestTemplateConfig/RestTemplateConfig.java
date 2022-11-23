package com.example.MonitorAgent.RestTemplateConfig;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateConfig {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int SOCKET_TIMEOUT = 10000;
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;
    private static final int MAX_ROUTE_PER_HOST = 133;
    private static final int MAX_TOTAL_CONNECTIONS = 400;


    @Bean("identityRestTemplate")
    public RestTemplate getIdentityRestTemplate() {
        logger.info("Iniciando configuracion de RestTemplate para el Identity");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(getClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean("motorRestTemplate")
    public RestTemplate getMotorRestTemplate() {
        logger.info("Iniciando configuracion de RestTemplate para el MOTOR");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(getClientHttpRequestFactory());
        return restTemplate;
    }
    
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        logger.debug("ConnectTimeout: {} ConnectionRequestTimeout {}: SocketTimeout {}:",CONNECTION_TIMEOUT,CONNECTION_REQUEST_TIMEOUT,SOCKET_TIMEOUT);
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setMaxConnPerRoute(MAX_ROUTE_PER_HOST)
                .setMaxConnTotal(MAX_TOTAL_CONNECTIONS)
                .setDefaultRequestConfig(getRequestConfig())
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
    }
}
