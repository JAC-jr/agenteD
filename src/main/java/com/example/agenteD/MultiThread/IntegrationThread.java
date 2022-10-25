package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class IntegrationThread extends GenericStatement implements Runnable{

    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM integration";

    @Override
    public void run() {

        do {
        try {
            genericStatement.createStatement(query);

            System.out.println("integration data returned ");

            while (genericStatement.rs.next()) {

                int integration_id = genericStatement.rs.getInt("integration_id");
                String integration_type =genericStatement.rs.getString("integration_type");
                String channel = genericStatement.rs.getString("channel");
                long test_interv = genericStatement.rs.getLong("test_interv");



                System.out.println("integration_id = " + integration_id + ", integration_type = " + integration_type
                        + ", channel = " + channel);

                try {
                    Thread.sleep(test_interv);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    } while (true);
    }
}
