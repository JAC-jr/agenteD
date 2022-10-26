package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ServiceThread extends GenericStatement implements Runnable {
    Logger logger = LoggerFactory.getLogger(ServiceThread.class);
    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM service";

    @Override
    public void run() {

        do {

            try {
                genericStatement.createStatement(query);

                logger.info("service data returned from database");

                while (genericStatement.rs.next()) {

                    int service_id = genericStatement.rs.getInt("service_id");
                    String description = genericStatement.rs.getString("description");
                    int application_id = genericStatement.rs.getInt("application_id");
                    String service_name = genericStatement.rs.getString("service_name");
                    String port = genericStatement.rs.getString("port");
                    long test_interv = genericStatement.rs.getLong("test_interv");

                    logger.info("service_id = {}, description = {}, application_id = {},service_name = {}, port = {}"
                            ,service_id, description, application_id, service_name, port);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        logger.error("ServiceThread interruption");
                        throw new RuntimeException(e);
                    }

                }
            } catch (SQLException e) {
                logger.error("ServiceThread failed to get data from database");
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
