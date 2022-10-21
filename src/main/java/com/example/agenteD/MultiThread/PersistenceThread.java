package com.example.agenteD.MultiThread;

import com.example.agenteD.Util.GenericStatement;

import java.sql.SQLException;

public class PersistenceThread extends GenericStatement implements Runnable {

    GenericStatement genericStatement = new GenericStatement();
    String query = "SELECT * FROM persistence";

    @Override
    public void run() {

        try {
            genericStatement.createStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
