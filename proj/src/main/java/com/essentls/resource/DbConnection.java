package com.essentls.resource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5434/ESSENTLS";
        String username = "postgres";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("PostgresSQL server connected");
            connection.close();

        } catch (Exception e) {
            System.out.println("Error in PostgresSQL server Connection");
            throw new RuntimeException(e);
        }

    }
}
