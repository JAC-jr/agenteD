package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericDriveClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Load_balancerThread extends GenericDriveClass implements Runnable {
    Logger logger = LoggerFactory.getLogger(Load_balancerThread.class);
    GenericDriveClass genericStatement = new GenericDriveClass();
    String query = "SELECT * FROM load_balancer";
    @Override
    public void run() {
        do {
            try {
                genericStatement.createStatement(query);

                logger.info("load_balancer data returned from database");

                while (genericStatement.rs.next()) {

                    int vserver_id = genericStatement.rs.getInt("vserver_id");
                    String description = genericStatement.rs.getString("description");
                    String ip_server = genericStatement.rs.getString("ip_server");
                    long test_interv = genericStatement.rs.getLong("test_interv");

                    logger.info("vserver_id = {}, description = {}, ip_server = {}"
                            ,vserver_id, description, ip_server);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        logger.error("Load_balancer interruption");
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                logger.error("Load_balancer failed to get data from database");
                throw new RuntimeException(e);
            }
        }while (true);
    }
}
