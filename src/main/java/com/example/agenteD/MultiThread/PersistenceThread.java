package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class PersistenceThread extends GenericStatement implements Runnable {
    Logger logger = LoggerFactory.getLogger(PersistenceThread.class);
    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM persistence";

    @Override
    public void run () {

        do {

            try {
                genericStatement.createStatement(query);

                logger.info("persistence data returned from database");

                while (genericStatement.rs.next()) {

                    int db_id = genericStatement.rs.getInt("db_id");
                    String db_name = genericStatement.rs.getString("db_name");
                    String description = genericStatement.rs.getString("description");
                    int application_id = genericStatement.rs.getInt("application_id");
                    String service_name = genericStatement.rs.getString("service_name");
                    String host = genericStatement.rs.getString("host");
                    String port = genericStatement.rs.getString("port");
                    long test_interv = genericStatement.rs.getLong("test_interv");

                    logger.info("db_id = {}, db_name = {}, description = {},application_id = {}, " +
                                    "service_name = {}, host = {}, port = {}"
                            ,db_id, db_name, description, application_id,
                            service_name, host, port);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        logger.error("PersistenceThread interruption");
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                logger.error("PersistenceThread failed to get data from database");
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
