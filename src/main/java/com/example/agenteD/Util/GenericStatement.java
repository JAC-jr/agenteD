package com.example.agenteD.Util;

import java.sql.*;

public class GenericStatement {
    public ResultSet rs;
    Connection c = null;
    PreparedStatement stmt = null;
    public ResultSet createStatement(String query_get) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/test_database",
                            "postgres", "cisneros");

            stmt = c.prepareStatement(query_get);
            rs = stmt.executeQuery();

            c.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

  /*  public void update_value (String query_post) throws SQLException {


        stmt =  c.createStatement();
        rs = stmt.executeUpdate(query_post);
    }*/
}

