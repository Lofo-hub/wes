package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/etudiant_db";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "1234";
    public static final String NOTES_URL = "http://localhost/Php/notes.php?id=";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}