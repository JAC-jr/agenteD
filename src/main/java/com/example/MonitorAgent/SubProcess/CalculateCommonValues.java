package com.example.MonitorAgent.SubProcess;

import com.example.MonitorAgent.Entity.Api;
import com.example.MonitorAgent.Entity.Servicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculateCommonValues {
    Logger logger = LoggerFactory.getLogger(CalculateCommonValues.class);
    public String statusApi(double response, Api api){

        String status = null;

        if(response == 0){
            logger.info("sin replicas funcionales");
            status = "sin replicas funcionales";
        }
        else if (response <= api.getMaxTestFailed()){
            logger.info("maximo de fallos excedido");
            status = "bad";
        }
        else if (api.getMaxTestFailed() < response && response <= api.getMinTestFailed()) {
            logger.info("minimo de fallos excedido");
            status = "healthy";
        }
        else if (api.getMinTestFailed() < response) {
            logger.info("respuesta exitosa");
            status = "good";
        }
        return status;
    }

    public String statusService(double response, Servicio service){

        String status = null;

        if(response == 0){
            logger.info("sin replicas funcionales");
            status = "sin replicas funcionales";
        }
        else if (response <= service.getMaxFailedTest()){
            logger.info("maximo de fallos excedido");
            status = "bad";
        }
        else if (service.getMaxFailedTest() < response && response <= service.getMinFailedTest()) {
            logger.info("minimo de fallos excedido");
            status = "healthy";
        }
        else if (service.getMinFailedTest() < response) {
            logger.info("respuesta exitosa");
            status = "good";
        }
        return status;
    }
}
