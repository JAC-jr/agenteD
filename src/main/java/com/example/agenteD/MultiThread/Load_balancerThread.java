package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class Load_balancerThread extends GenericStatement implements Runnable {
    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM load_balancer";
    @Override
    public void run() {

        do {

            try {
                genericStatement.createStatement(query);

                System.out.println("load_balancer data returned ");

                while (genericStatement.rs.next()) {

                    int vserver_id = genericStatement.rs.getInt("vserver_id");
                    String description = genericStatement.rs.getString("description");
                    String ip_server = genericStatement.rs.getString("ip_server");
                    long test_interv = genericStatement.rs.getLong("test_interv");


                    System.out.println("vserver_id = " + vserver_id + ", description = " + description
                            + ", ip_server = " + ip_server);

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
