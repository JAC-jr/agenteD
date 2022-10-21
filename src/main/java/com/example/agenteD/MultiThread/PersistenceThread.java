package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class PersistenceThread extends GenericStatement implements Runnable {

    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM persistence";

    @Override
    public void run () {

        try {
            genericStatement.createStatement(query);

            System.out.println("persistence data returned ");

            while (genericStatement.rs.next()) {

                int db_id = genericStatement.rs.getInt("db_id");
                String db_name =genericStatement.rs.getString("db_name");
                String description = genericStatement.rs.getString("description");
                int application_id = genericStatement.rs.getInt("application_id");
                String service_name =genericStatement.rs.getString("service_name");
                String host =genericStatement.rs.getString("host");
                String port =genericStatement.rs.getString("port");

                System.out.println("db_id = " + db_id + ", db_name = " + db_name
                        + ", description = " + description + ", application_id = " + application_id
                        + ", service_name = " + service_name + ", service_name = " + service_name
                        + ", host = " + host + ", port = " + port );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
