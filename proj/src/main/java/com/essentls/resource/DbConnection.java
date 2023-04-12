package com.essentls.resource;

import com.sun.net.httpserver.HttpServer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public static void main(String[] args) {

        /**
         * 
         * PostgreSQL Connection
         * I tested in my device its working
         * for test in your computer
         * you have to download the database file in your computer
         * you can find SQL file here "essentls\proj\src\main\db" in our project
         * After downloading it, you have to restore it in your pgAdmin
         * Remember to use postgresql version 10 in pgAdmin
         * After restoring, check the Port number, in my case it is 5334
         * if it is different in your system, make the change in URL
         * also check the username and password
         * in my case username:postgres  and password: admin
         *
         */
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            //URL, Username, Password, Always check these things in your local computer
            //If you found something different, make change here
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
