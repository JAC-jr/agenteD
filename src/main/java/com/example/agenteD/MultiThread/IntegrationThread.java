package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class IntegrationThread extends GenericStatement implements Runnable{
    Logger logger = LoggerFactory.getLogger(IntegrationThread.class);
    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM integration";

    @Override
    public void run() {

        do {
            try {
                genericStatement.createStatement(query);

                logger.info("integration data returned from database");

                while (genericStatement.rs.next()) {

                    int integration_id = genericStatement.rs.getInt("integration_id");
                    String integration_type =genericStatement.rs.getString("integration_type");
                    String channel = genericStatement.rs.getString("channel");
                    long test_interv = genericStatement.rs.getLong("test_interv");

                    logger.info("integration_id = {}, integration_type = {}, channel = {}"
                        ,integration_id, integration_type, channel);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        logger.error("IntegrationThread interruption");
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                logger.error("IntegrationThread failed to get data from database");
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
