package com.example.MonitorAgent.InterrogationMethods.PersistencesMethod;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.*;


@Service
public class GetConnectionData {

    Logger logger = LoggerFactory.getLogger(GetConnectionData.class);


    public String getDATA (String UrlPersistence , String UserName , String Password, String SQL, String DBType ) {



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

        } catch (SQLException e) {
            logger.info("SQLException", e);
            response = e.toString();
            return response;

        } catch (ClassNotFoundException e) {
            logger.info("response = {}", e.toString());
            response = e.toString();
            return response;

        }
        return response;
    }

}