package com.essentls.resource;

import com.sun.net.httpserver.HttpServer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public static void main(String[] args) {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/ESSENTLS",
                    "postgres", "admin");

            if(connection != null){
                System.out.println("PostgresSQL server connected");
            }else {
                System.out.println("Connection is Null, or Not found");
            }

        } catch (Exception e) {
            System.out.println("Error in PostgresSQL server Connection");
            throw new RuntimeException(e);
        }

    }
}
