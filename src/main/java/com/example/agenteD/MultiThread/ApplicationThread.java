package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class ApplicationThread extends GenericStatement implements Runnable {
    GenericStatement genericStatement = new GenericStatement();
    String query_get = "SELECT * FROM Application";
   // String query_post= "insert into personas values(" + status + ")";

    @Override
    public void run() {

        do {

        try {
            genericStatement.createStatement(query_get);

            System.out.println("application data returned ");

            while (genericStatement.rs.next()) {

                int application_id = genericStatement.rs.getInt("application_id");
                String application_name =genericStatement.rs.getString("application_name");
                String description = genericStatement.rs.getString("description");
                long test_interv = genericStatement.rs.getLong("test_interv");


                System.out.println("application_id = " + application_id + ", application_name = " + application_name
                        + ", description = " + description);

                try {
                    Thread.sleep(test_interv);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
          //  genericStatement.update_value(query_post);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        } while (true);
    }
}
