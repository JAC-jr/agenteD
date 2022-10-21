package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class ApplicationThread extends GenericStatement implements Runnable {
    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM Application";
    @Override
    public void run() {

        try {
            genericStatement.createStatement(query);

            System.out.println("application data returned ");

            while (genericStatement.rs.next()) {

                int application_id = genericStatement.rs.getInt("application_id");
                String application_name =genericStatement.rs.getString("application_name");
                String description = genericStatement.rs.getString("description");


                System.out.println("application_id = " + application_id + ", application_name = " + application_name
                        + ", description = " + description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
