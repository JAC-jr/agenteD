package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ApplicationThread extends GenericStatement implements Runnable {
    Logger logger = LoggerFactory.getLogger(ApplicationThread.class);
    GenericStatement genericStatement = new GenericStatement();
    String query_get = "SELECT * FROM Application";

    @Override
    public void run() {
        do {

            try {
                genericStatement.createStatement(query_get);

                logger.info("application data returned from database");

                while (genericStatement.rs.next()) {

                    int application_id = genericStatement.rs.getInt("application_id");
                    String application_name =genericStatement.rs.getString("application_name");
                    String description = genericStatement.rs.getString("description");
                    long test_interv = genericStatement.rs.getLong("test_interv");

                    logger.info("application_id = {}, application_name = {}, description = {}"
                        ,application_id, application_name, description);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        logger.error("ApplicationThread interruption");
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                logger.error("ApplicationThread failed to get data from database");
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
