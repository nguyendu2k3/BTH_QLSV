package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class    JDBCUtil {
    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-K5U7RTE\\SQLEXPRESS:1433;database=QLSV";
    private static final String USER = "sa";
    private static final String PASSWORD = "123"; // Thay đổi mật khẩu của bạn

    public static Connection getConnection() throws SQLException {
        try {
            // Đăng ký driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Tạo kết nối
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver không tìm thấy", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
