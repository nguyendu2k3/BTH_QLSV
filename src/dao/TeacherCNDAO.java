package dao;

import model.TeacherCN;
import java.sql.*;
import java.util.ArrayList;
import database.DatabaseConnection;

public class TeacherCNDAO {
    public ArrayList<TeacherCN> getAllTeachers() {
        ArrayList<TeacherCN> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM TeacherCN";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TeacherCN t = new TeacherCN(
                        rs.getInt("IDTeacher"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender")
                );
                list.add(t);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addTeacher(TeacherCN t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO TeacherCN (IDTeacher, Name, Age, Gender) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, t.getIDTeacher());
            pst.setString(2, t.getName());
            pst.setInt(3, t.getAge());
            pst.setString(4, t.getGender());
            int result = pst.executeUpdate();
            DatabaseConnection.closeConnection(conn);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateTeacher(TeacherCN t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE TeacherCN SET Name=?, Age=?, Gender=? WHERE IDTeacher=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.getName());
            pst.setInt(2, t.getAge());
            pst.setString(3, t.getGender());
            pst.setInt(4, t.getIDTeacher());
            int result = pst.executeUpdate();
            DatabaseConnection.closeConnection(conn);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTeacher(int IDTeacher) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM TeacherCN WHERE IDTeacher=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, IDTeacher);
            int result = pst.executeUpdate();
            DatabaseConnection.closeConnection(conn);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public TeacherCN findTeacherById(int IDTeacher) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM TeacherCN WHERE IDTeacher=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, IDTeacher);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                TeacherCN t = new TeacherCN(
                        rs.getInt("IDTeacher"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender")
                );
                DatabaseConnection.closeConnection(conn);
                return t;
            }
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}