package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DatabaseConnection;
import model.Sinhvien;

public class SinhvienDAO {
    // lấy sinh viên trong lớp theo ID và IdClass

    // Thêm sinh viên mới
    public int themSinhVien(Sinhvien sinhvien) {
        int ketQua = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO SinhVien (ID, Name, Age, Gender, Address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, sinhvien.getID());
            pst.setString(2, sinhvien.getName());
            pst.setInt(3, sinhvien.getAge());
            pst.setString(4, sinhvien.getGender());
            pst.setString(5, sinhvien.getAddress());
            ketQua = pst.executeUpdate();
            System.out.println("Thêm sinh viên thành công: " + sinhvien);
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sinh viên: " + e.getMessage());
        }
        return ketQua;
    }

    // Sửa thông tin sinh viên
    public int updateSinhVien(Sinhvien sinhvien) {
        int ketQua = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE SinhVien SET Name = ?, Age = ?, Gender = ?, Address = ? WHERE IDSinhVien = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sinhvien.getName());
            pst.setInt(2, sinhvien.getAge());
            pst.setString(3, sinhvien.getGender());
            pst.setString(4, sinhvien.getAddress());
            pst.setInt(5, sinhvien.getID());
            ketQua = pst.executeUpdate();
            System.out.println("Sửa thông tin sinh viên thành công: " + sinhvien);
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa sinh viên: " + e.getMessage());
        }
        return ketQua;
    }

    // Xóa sinh viên theo IDSinhVien
    public int xoaSinhVien(int IDSinhVien) {
        int ketQua = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM SinhVien WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, IDSinhVien);
            ketQua = pst.executeUpdate();
            System.out.println("Xóa sinh viên thành công với IDSinhVien: " + IDSinhVien);
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sinh viên: " + e.getMessage());
        }
        return ketQua;
    }

    // Lấy danh sách tất cả sinh viên
    public ArrayList<Sinhvien> layDanhSachSinhVien() {
        ArrayList<Sinhvien> danhSach = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            // Thêm debug để kiểm tra kết nối
            if (conn == null) {
                System.err.println("Lỗi kết nối: Không thể kết nối đến database");
                return danhSach;
            }

            // Thêm schema dbo nếu cần
            String sql = "SELECT * FROM dbo.SinhVien";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Thêm debug để kiểm tra có dữ liệu không
            boolean coData = false;

            while (rs.next()) {
                coData = true;
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");

                Sinhvien sinhvien = new Sinhvien(id, name, age, gender, address);
                danhSach.add(sinhvien);
            }

            if (!coData) {
                System.out.println("Không có dữ liệu sinh viên trong database");
            }

            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để debug chi tiết hơn
        }

        return danhSach;
    }

    // Tìm sinh viên theo IDSinhVien
    public Sinhvien timSinhVienTheoID(int IDSinhVien) {
        Sinhvien sinhvien = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM SinhVien WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, IDSinhVien);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                sinhvien = new Sinhvien(id, name, age, gender, address);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm sinh viên: " + e.getMessage());
        }
        return sinhvien;
    }

    // Tìm sinh viên theo tên lớp
    public ArrayList<Sinhvien> layDanhSachSinhVienTheoTenLop(String tenLop) {
        ArrayList<Sinhvien> danhSach = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT sv.* FROM SinhVien sv " +
                    "JOIN ClassDTO c ON sv.IDClass = c.IDClass " +
                    "WHERE c.NameClass = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tenLop);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                Sinhvien sinhvien = new Sinhvien(id, name, age, gender, address);
                danhSach.add(sinhvien);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sinh viên theo tên lớp: " + e.getMessage());
        }
        return danhSach;
    }
}