package org.example.utils;

import java.sql.*;

/**
 * Clase DatabaseConnection, se encarga de la conexión con la base de datos
 */
public class DatabaseConnection {
    private static Connection con = null;

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost/poo_hospital";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
