package com.ahmed.smartphonejava.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    static Connection conn=null;
    public static Connection connecter() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_smartphone", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
