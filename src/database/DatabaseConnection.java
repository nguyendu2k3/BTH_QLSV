package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://DESKTOP-K5U7RTE;database=QLSV;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection getConnection() throws SQLException {
        try {
            // Load driver cho SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQL Server JDBC Driver không được tìm thấy", e);
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
