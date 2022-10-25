package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class ApiThread extends GenericStatement implements Runnable {
    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM api";
    @Override
    public void run() {

        do {

            try {
                genericStatement.createStatement(query);

                System.out.println("api data returned ");

                while (genericStatement.rs.next()) {

                    int api_id = genericStatement.rs.getInt("api_id");
                    int application_id = genericStatement.rs.getInt("application_id");
                    String description = genericStatement.rs.getString("description");
                    String service_name = genericStatement.rs.getString("service_name");
                    String name_space = genericStatement.rs.getString("name_space");
                    long test_interv = genericStatement.rs.getLong("test_interv");


                    System.out.println("api_id = " + api_id + ", application_id = " + application_id
                            + ", description = " + description + ", service_name = " + service_name
                            + ", name_space = " + name_space);

                    try {
                        Thread.sleep(test_interv);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }while (true);
    }
}


