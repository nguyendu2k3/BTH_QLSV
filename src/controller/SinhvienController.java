package controller;

import dao.SinhvienDAO;
import model.Sinhvien;

import java.util.List;

public class SinhvienController {

    private final SinhvienDAO sinhvienDAO;

    // Constructor
    public SinhvienController() {
        this.sinhvienDAO = new SinhvienDAO();
    }

    // Thêm sinh viên
    public boolean themSinhVien(Sinhvien sinhvien) {
        if (!isValidSinhVien(sinhvien)) {
            System.err.println("Dữ liệu sinh viên không hợp lệ: " + sinhvien);
            return false;
        }
        int result = sinhvienDAO.themSinhVien(sinhvien);
        return result > 0;
    }

    // Sửa thông tin sinh viên
    public boolean suaSinhVien(Sinhvien sinhvien) {
        if (!isValidSinhVien(sinhvien)) {
            System.err.println("Dữ liệu sinh viên không hợp lệ: " + sinhvien);
            return false;
        }
        int result = sinhvienDAO.updateSinhVien(sinhvien);
        return result > 0;
    }

    // Xóa sinh viên theo ID
    public boolean xoaSinhVien(int idSinhVien) {
        if (idSinhVien <= 0) {
            System.err.println("ID sinh viên không hợp lệ: " + idSinhVien);
            return false;
        }
        int result = sinhvienDAO.xoaSinhVien(idSinhVien);
        return result > 0;
    }

    // Lấy danh sách tất cả sinh viên
    public List<Sinhvien> layDanhSachSinhVien() {
        return sinhvienDAO.layDanhSachSinhVien();
    }

    // Tìm sinh viên theo ID
    public Sinhvien timSinhVienTheoID(int idSinhVien) {
        if (idSinhVien <= 0) {
            System.err.println("ID sinh viên không hợp lệ: " + idSinhVien);
            return null;
        }
        return sinhvienDAO.timSinhVienTheoID(idSinhVien);
    }

    // lấy danh sách sinh viên theo tên lớp
    public List<Sinhvien> layDanhSachSinhVienTheoTenLop(String tenLop) {
        if (tenLop == null || tenLop.trim().isEmpty()) {
            System.err.println("Tên lớp không hợp lệ: " + tenLop);
            return null;
        }
        return sinhvienDAO.layDanhSachSinhVienTheoTenLop(tenLop);
    }

    // Xác thực dữ liệu sinh viên
    private boolean isValidSinhVien(Sinhvien sinhvien) {
        if (sinhvien == null) {
            System.err.println("Sinh viên không được null.");
            return false;
        }
        if (sinhvien.getName() == null || sinhvien.getName().trim().isEmpty()) {
            System.err.println("Tên sinh viên không được để trống.");
            return false;
        }
        if (sinhvien.getAge() <= 0 || sinhvien.getAge() > 100) {
            System.err.println("Tuổi sinh viên không hợp lệ: " + sinhvien.getAge());
            return false;
        }
        if (sinhvien.getGender() == null || sinhvien.getGender().trim().isEmpty()) {
            System.err.println("Giới tính sinh viên không được để trống.");
            return false;
        }
        if (sinhvien.getAddress() == null || sinhvien.getAddress().trim().isEmpty()) {
            System.err.println("Địa chỉ sinh viên không được để trống.");
            return false;
        }
        return true;
    }
}