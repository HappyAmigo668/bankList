package com.potapov.bankList.connection;

import java.sql.*;

public class BankListJDBC {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/banklist?useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "root";


    public Connection getConnection(){

        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver error");
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.err.println("Connection created.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection error.");
        }

        return connection;
    }

    public void closingConnectionAndStatement(PreparedStatement preparedStatement, Connection connection) {
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
