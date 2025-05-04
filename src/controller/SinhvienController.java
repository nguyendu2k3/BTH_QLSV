package controller;

import dao.SinhvienDAO;
import model.Sinhvien;
import java.util.ArrayList;
import java.util.List;

public class SinhvienController {
    private SinhvienDAO sinhvienDAO;

    public SinhvienController() {
        this.sinhvienDAO = new SinhvienDAO();
    }

    // Thêm sinh viên mới
    public boolean themSinhVien(Sinhvien sinhvien) {
        return sinhvienDAO.themSinhVien(sinhvien) > 0;
    }

    // Cập nhật thông tin sinh viên
    public boolean capNhatSinhVien(Sinhvien sinhvien) {
        return sinhvienDAO.updateSinhVien(sinhvien) > 0;
    }

    // Xóa sinh viên
    public boolean xoaSinhVien(int idSinhVien) {
        return sinhvienDAO.xoaSinhVien(idSinhVien) > 0;
    }

    // Lấy danh sách tất cả sinh viên
    public List<Sinhvien> layDanhSachSinhVien() {
        return sinhvienDAO.layDanhSachSinhVien();
    }

    // Tìm sinh viên theo ID
    public Sinhvien timSinhVienTheoID(int idSinhVien) {
        return sinhvienDAO.timSinhVienTheoID(idSinhVien);
    }

    // Lấy danh sách sinh viên theo tên lớp
    public List<Sinhvien> layDanhSachSinhVienTheoTenLop(String tenLop) {
        return sinhvienDAO.layDanhSachSinhVienTheoTenLop(tenLop);
    }
    // Lấy danh sách sinh viên theo ID lớp
    public List<Sinhvien> layDanhSachSinhVienTheoIDLop(int idLop) {
        return sinhvienDAO.layDanhSachSinhVienTheoIDLop(idLop);
    }
    // Thêm sinh viên vào lớp
    public boolean themSinhVienVaoLop(int idSinhVien, int idLop) {
        return sinhvienDAO.themSinhVienVaoLop(idSinhVien, idLop);
    }

    // Xóa sinh viên khỏi lớp
    public boolean xoaSinhVienKhoiLop(int idSinhVien, int idLop) {
        return sinhvienDAO.xoaSinhVienKhoiLop(idSinhVien, idLop);
    }

    // Lấy danh sách lớp của sinh viên
    public ArrayList<Integer> layDanhSachLopCuaSinhVien(int idSinhVien) {
        return sinhvienDAO.layDanhSachLopCuaSinhVien(idSinhVien);
    }

    // Kiểm tra sinh viên có thuộc lớp không
    public boolean kiemTraSinhVienThuocLop(int idSinhVien, int idLop) {
        ArrayList<Integer> danhSachLop = layDanhSachLopCuaSinhVien(idSinhVien);
        return danhSachLop.contains(idLop);
    }
}