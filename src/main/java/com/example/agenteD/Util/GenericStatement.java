package com.example.agenteD.Util;

import java.sql.*;

public class GenericStatement {

    public ResultSet createStatement(String query) throws SQLException {

        Connection c = null;
        ResultSet rs;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/test_database",
                            "postgres", "cisneros");

            System.out.println("Opened database successfully");

            PreparedStatement preparedStatement = c.prepareStatement(query);
            rs = preparedStatement.executeQuery();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return rs;

    }

}

