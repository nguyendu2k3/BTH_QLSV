package dao;

import database.DatabaseConnection;
import model.ClassDTO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    //Thêm Lớp mới
    public boolean themLop(ClassDTO classDTO) {
        String sql = "INSERT INTO Class (IDClass, NameClass, IDTeacher) VALUES ((SELECT ISNULL(MAX(IDClass), 0) + 1 FROM Class), ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, classDTO.getNameClass());
            pst.setInt(2, classDTO.getIDTeacher());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                // Get the generated ID if needed
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT MAX(IDClass) FROM Class")) {
                    if (rs.next()) {
                        classDTO.setIDClass(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error adding class: " + e.getMessage());
            return false;
        }
    }
    // Update ClassDTO
    public boolean updateLop(ClassDTO classDTO) {
        String sql = "UPDATE Class SET NameCLass = ?, IDTeacher = ? WHERE IDClass = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, classDTO.getNameClass());
            pst.setInt(2, classDTO.getIDTeacher());
            pst.setInt(3, classDTO.getIDClass());

            int Rows = pst.executeUpdate();
            return Rows > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật lớp: " + e.getMessage());
            return false;
        }
    }
    //xóa lớp
    public boolean xoaLop(int classID) {
        String sql = "DELETE FROM Class WHERE IDClass = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, classID);

            int Rows = pst.executeUpdate();
            return Rows > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa lớp: " + e.getMessage());
            return false;
        }
    }

    //Lấy danh sách lớp học theo IDClass
    public List<ClassDTO> getClassByID(int classID) {

        String sql = "SELECT * FROM Class WHERE IDClass = ?";
        List<ClassDTO> classList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, classID);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    ClassDTO classDTO = new ClassDTO();
                    classDTO.setIDClass(rs.getInt("IDClass"));
                    classDTO.setNameClass(rs.getString("NameClass"));
                    classDTO.setIDTeacher(rs.getInt("IDTeacher"));

                    classList.add(classDTO);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classList;
    }

    // Lấy danh sách tất cả các lớp học
    public List<ClassDTO> getAllClass() {
        String sql =
                "SELECT c.IDClass, c.NameClass, c.IDTeacher, t.Name AS TeacherCNName " +
                        "FROM Class c " +
                        "LEFT JOIN TeacherCN t ON c.IDTeacher = t.IDTeacher";
        List<ClassDTO> classList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                ClassDTO classDTO = new ClassDTO();
                classDTO.setIDClass(rs.getInt("IDClass"));
                classDTO.setNameClass(rs.getString("NameClass"));
                classDTO.setIDTeacher(rs.getInt("IDTeacher"));
                classDTO.setTeacherCNName(rs.getString("TeacherCNName"));  // use new setter
                classList.add(classDTO);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách lớp học: " + e.getMessage());
        }
        return classList;
    }
}
