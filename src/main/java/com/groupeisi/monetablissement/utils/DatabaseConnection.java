//package com.groupeisi.monetablissement.utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//
//        private static final String URL = "jdbc:postgresql://localhost:5432/GestionEtablissement";
//        private static final String USER = "postgres";
//        private static final String PASSWORD = "mami2002";
//
//        public static Connection getConnection() throws SQLException {
//            return DriverManager.getConnection(URL, USER, PASSWORD);
//        }

package com.groupeisi.monetablissement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/GestionEtablissement";
        private static final String USER = "postgres";
        private static final String PASSWORD = "mami2002";

        private static Connection connection;


        public static Connection getConnection() throws SQLException {
                if (connection == null || connection.isClosed()) {
                        try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        } catch (ClassNotFoundException e) {
                                System.err.println("PostgreSQL JDBC Driver not found!");
                                e.printStackTrace();
                        }
                }
                return connection;
        }

        public static void closeConnection() {
                if (connection != null) {
                        try {
                                connection.close();
                        } catch (SQLException e) {
                                System.err.println("Error closing connection!");
                                e.printStackTrace();
                        }
                }
        }
}
