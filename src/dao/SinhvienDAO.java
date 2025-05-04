package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Sinhvien;

public class SinhvienDAO {
    // Thêm sinh viên mới
    public int themSinhVien(Sinhvien sinhvien) {
        int ketQua = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO SinhVien (ID, Name, Age, Gender, Address, IDClass) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, sinhvien.getID());
            pst.setString(2, sinhvien.getName());
            pst.setInt(3, sinhvien.getAge());
            pst.setString(4, sinhvien.getGender());
            pst.setString(5, sinhvien.getAddress());
            pst.setInt(6, sinhvien.getIDClass());
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
            String sql = "UPDATE SinhVien SET Name = ?, Age = ?, Gender = ?, Address = ?, IDClass = ? WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sinhvien.getName());
            pst.setInt(2, sinhvien.getAge());
            pst.setString(3, sinhvien.getGender());
            pst.setString(4, sinhvien.getAddress());
            pst.setInt(5, sinhvien.getIDClass());
            pst.setInt(6, sinhvien.getID());
            ketQua = pst.executeUpdate();
            System.out.println("Sửa thông tin sinh viên thành công: " + sinhvien);
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa sinh viên: " + e.getMessage());
        }
        return ketQua;
    }

    // Xóa sinh viên theo ID
    public int xoaSinhVien(int IDSinhVien) {
        int ketQua = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM SinhVien WHERE ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, IDSinhVien);
            ketQua = pst.executeUpdate();
            System.out.println("Xóa sinh viên thành công với ID: " + IDSinhVien);
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
            if (conn == null) {
                System.err.println("Lỗi kết nối: Không thể kết nối đến database");
                return danhSach;
            }

            String sql = "SELECT * FROM dbo.SinhVien";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            boolean coData = false;

            while (rs.next()) {
                coData = true;
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                int idClass = 0;

                try {
                    idClass = rs.getInt("IDClass");
                } catch (SQLException e) {
                    // IDClass có thể không tồn tại trong một số bản ghi cũ
                    System.out.println("Không tìm thấy IDClass cho sinh viên " + id + ": " + e.getMessage());
                }

                Sinhvien sinhvien = new Sinhvien(id, name, age, gender, address, idClass);
                danhSach.add(sinhvien);
            }

            if (!coData) {
                System.out.println("Không có dữ liệu sinh viên trong database");
            }

            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
            e.printStackTrace();
        }

        return danhSach;
    }

    // Tìm sinh viên theo ID
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
                int idClass = 0;

                try {
                    idClass = rs.getInt("IDClass");
                } catch (SQLException e) {
                    // IDClass có thể không tồn tại trong một số bản ghi cũ
                }

                sinhvien = new Sinhvien(id, name, age, gender, address, idClass);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm sinh viên: " + e.getMessage());
        }
        return sinhvien;
    }

    // Tìm sinh viên theo tên lớp
    public List<Sinhvien> layDanhSachSinhVienTheoTenLop(String tenLop) {
        List<Sinhvien> danhSach = new ArrayList<>();
        String sql =
                "SELECT sv.ID, sv.Name, sv.Age, sv.Gender, sv.Address, t.IDClass " +
                        "FROM SinhVien sv " +
                        "JOIN Thuoc t ON sv.ID = t.ID " +
                        "JOIN Lop l ON t.IDClass = l.IDClass " +
                        "WHERE l.NameClass = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tenLop);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                danhSach.add(new Sinhvien(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getInt("IDClass")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sinh viên theo tên lớp: " + e.getMessage());
        }
        return danhSach;
    }


        // Thêm sinh viên vào lớp (tạo mối quan hệ)
        public boolean themSinhVienVaoLop(int idSinhVien, int idLop) {
        boolean ketQua = false;
        try {
            Connection conn = DatabaseConnection.getConnection();

            // Kiểm tra xem quan hệ đã tồn tại chưa
            String checkSql = "SELECT * FROM Thuoc WHERE ID = ? AND IDClass = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, idSinhVien);
            checkStmt.setInt(2, idLop);
            ResultSet rs = checkStmt.executeQuery();

            // Nếu quan hệ chưa tồn tại, thêm mới
            if (!rs.next()) {
                String sql = "INSERT INTO Thuoc (ID, IDClass) VALUES (?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, idSinhVien);
                pst.setInt(2, idLop);
                int rowsAffected = pst.executeUpdate();

                ketQua = (rowsAffected > 0);
                System.out.println("Thêm sinh viên vào lớp " + (ketQua ? "thành công" : "thất bại") +
                        ": SV ID=" + idSinhVien + ", Lớp ID=" + idLop);
            } else {
                System.out.println("Sinh viên đã thuộc lớp này!");
            }

            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sinh viên vào lớp: " + e.getMessage());
        }
        return ketQua;
    }

        // Xóa sinh viên khỏi lớp
        public boolean xoaSinhVienKhoiLop(int idSinhVien, int idLop) {
        boolean ketQua = false;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM Thuoc WHERE ID = ? AND IDClass = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idSinhVien);
            pst.setInt(2, idLop);
            int rowsAffected = pst.executeUpdate();

            ketQua = (rowsAffected > 0);

            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sinh viên khỏi lớp: " + e.getMessage());
        }
        return ketQua;
    }

        // Lấy danh sách lớp của sinh viên
        public ArrayList<Integer> layDanhSachLopCuaSinhVien(int idSinhVien) {
            ArrayList<Integer> danhSachLop = new ArrayList<>();

            try {
                Connection conn = DatabaseConnection.getConnection();
                // Use Thuoc table to get class IDs for a student
                String sql = "SELECT IDClass FROM Thuoc WHERE ID = ?";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, idSinhVien);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    danhSachLop.add(rs.getInt("IDClass"));
                }

                // Don't log error if student simply isn't in any classes
                if (danhSachLop.isEmpty()) {
                    System.out.println("Student " + idSinhVien + " is not enrolled in any classes");
                }

                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("Error retrieving class list for student " + idSinhVien + ": " + e.getMessage());
            }

            return danhSachLop;
        }

    public List<Sinhvien> layDanhSachSinhVienTheoIDLop(int idLop) {
        List<Sinhvien> danhSach = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT sv.ID, sv.Name, sv.Age, sv.Gender, sv.Address "
                    + "FROM SinhVien sv JOIN Thuoc t ON sv.ID = t.ID "
                    + "WHERE t.IDClass = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idLop);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Sinhvien sv = new Sinhvien(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        idLop
                );
                danhSach.add(sv);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy sinh viên theo ID lớp: " + e.getMessage());
        }
        return danhSach;
    }
}