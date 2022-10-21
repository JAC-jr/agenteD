package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository

public class ApplicationThread extends GenericStatement implements Runnable {

    GenericStatement genericStatement = new GenericStatement();

    String query = "SELECT * FROM Application";

    @Override
    public void run() {

        try {
            genericStatement.createStatement(query);

            
            while (rs.next()) {

                System.out.println("Column 1 returned ");
                int id = rs.getInt("application_id");
                System.out.println("id = " + id);
                String name = rs.getString("application_name");
                System.out.println(" name = " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
