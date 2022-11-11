package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericDriveClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ApiThread extends GenericDriveClass implements Runnable {
    Logger logger = LoggerFactory.getLogger(ApiThread.class);
    GenericDriveClass genericStatement = new GenericDriveClass();
    String query = "SELECT * FROM api";
    @Override
    public void run() {

        do {

            try {
                genericStatement.createStatement(query);

                logger.info("api data returned from database");

                while (genericStatement.rs.next()) {

                    int api_id = genericStatement.rs.getInt("api_id");
                    int application_id = genericStatement.rs.getInt("application_id");
                    String description = genericStatement.rs.getString("description");
                    String service_name = genericStatement.rs.getString("service_name");
                    String name_space = genericStatement.rs.getString("name_space");
                    long test_interv = genericStatement.rs.getLong("test_interv");

                    logger.info("api_id = {}, application_id = {}, description = {},service_name = {}, name_space = {}"
                            ,api_id, application_id, description, service_name, name_space);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        logger.error("ApiThread interruption");
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                logger.error("ApiThread failed to get data from database");
                throw new RuntimeException(e);
            }
        }while (true);
    }
}


