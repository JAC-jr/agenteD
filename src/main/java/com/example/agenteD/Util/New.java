package com.example.agenteD.Util;


import java.sql.*;

public class New {

    public ResultSet createStatement() throws SQLException {
        
        Connection c = jdbc:postgresql://localhost:5432/test_database
        String query = "SELECT * FROM `Application` WHERE `application_id`= ? AND `application_name`= ?";
        PreparedStatement stmt = c.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            System.out.print("Column 1 returned ");
            int id = rs.getInt("application_id");
            System.out.print("id = " + id);
            int name = rs.getInt("application_name");
            System.out.print("name = " + name);
        }

        return rs;
    }
}
