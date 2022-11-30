package com.example.MonitorAgent.RestTemplateConfig;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class RestTemplateConfig {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int SOCKET_TIMEOUT = 5000;
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;
    private static final int MAX_ROUTE_PER_HOST = 10;
    private static final int MAX_TOTAL_CONNECTIONS = 10;


    @Bean("RestTemplate")
    public RestTemplate getRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        logger.info("creando el bean de configuracion de rest template");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(getClientHttpRequestFactory());
        return restTemplate;
    }


    private ClientHttpRequestFactory getClientHttpRequestFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        logger.debug("ConnectTimeout: {} ConnectionRequestTimeout {}: SocketTimeout {}:",CONNECTION_TIMEOUT,CONNECTION_REQUEST_TIMEOUT,SOCKET_TIMEOUT);

        TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setMaxConnPerRoute(MAX_ROUTE_PER_HOST)
                .setMaxConnTotal(MAX_TOTAL_CONNECTIONS)
                .setDefaultRequestConfig(getRequestConfig())
                .setSSLSocketFactory(csf)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    private RequestConfig getRequestConfig() {
           logger.info("comprobando la configuracion de rest template");
        return RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
    }
}