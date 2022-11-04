package com.example.agenteD.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class GenericStatement extends LogsConfig {

    LogsConfig logsConfig = new LogsConfig();
    public ResultSet rs;
    Connection c = null;
    PreparedStatement stmt = null;
    Logger logger = LoggerFactory.getLogger(getClass());

    public ResultSet createStatement(String query_get) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/test_database",
                            "postgres", "cisneros");

            logger.info("try to connect database");

            stmt = c.prepareStatement(query_get);
            rs = stmt.executeQuery();

            c.close();

        } catch (ClassNotFoundException e) {
            logger.error("connection to database failure");
            throw new RuntimeException(e);
        }
        return rs;
    }
}

