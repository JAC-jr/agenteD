package com.example.MonitorAgent.InterrogationMethods.PersistencesMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class GetConnectionData {
    Logger logger = LoggerFactory.getLogger(GetConnectionData.class);
    public String getDATA(String UrlPersistence, String UserName, String Password, String SQL, String DBType) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String response = null;

        try {

            Class.forName(DBType);

            connection = DriverManager.getConnection(UrlPersistence, UserName, Password);

            preparedStatement = connection.prepareStatement(SQL);

            rs = preparedStatement.executeQuery();

            logger.info("response = {}", rs.toString());

            response = rs.next() ? rs.getString(1) : null;

            rs.close();
            connection.close();

            if (response.isEmpty()) {
                response = "DOWN";
            } else {
                response = "OK";
            }

        } catch (SQLException e) {
            logger.info("SQLException", e);
            response = "DOWN";
            return response;

        } catch (ClassNotFoundException e) {
            logger.info("response = {}", e.toString());
            response = "error Driver DB";
            return response;
        }

        return response;
    }
}