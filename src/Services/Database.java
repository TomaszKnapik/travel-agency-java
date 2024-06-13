package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost/travelagency",
            DB_USER = "root",
            DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}