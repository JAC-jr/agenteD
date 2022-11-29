package com.example.MonitorAgent.NextStep;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Logger;

public class PingTest {

    Logger logger = Logger.getLogger(PingTest.class.getName());

    public boolean testPIng(String BaseIP) throws IOException {

        try {
            InetAddress address = InetAddress.getByName(BaseIP);

            boolean reachable = address.isReachable(5000);
            if (reachable) {
                logger.info("Ping exitoso a: " + BaseIP);
            } else {
                logger.info("Ping fallido a: " + BaseIP);
            }
            return reachable;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}