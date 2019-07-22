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
            System.out.println("Driver error");
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Connection created.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error.");
        }

        return connection;
    }
}
