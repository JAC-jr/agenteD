package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class ServiceThread extends GenericStatement implements Runnable {

    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM service";

    @Override
    public void run() {

        try {
            genericStatement.createStatement(query);

            System.out.println("service data returned ");

            while (genericStatement.rs.next()) {

                int service_id = genericStatement.rs.getInt("service_id");
                String description = genericStatement.rs.getString("description");
                int application_id = genericStatement.rs.getInt("application_id");
                String service_name =genericStatement.rs.getString("service_name");
                String port =genericStatement.rs.getString("port");

                System.out.println("service_id = " + service_id + ", description = " + description
                        + ", application_id = " + application_id +", service_name = " + service_name
                        +", port = " + port );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
