package controller;

import dao.ClassDAO;
import model.ClassDTO;

import java.util.List;

public class ClassController {
    private final ClassDAO classDAO;

    // Constructor
    public ClassController() {
        this.classDAO = new ClassDAO();
    }

    // Thêm lớp mới
    public boolean themLop(ClassDTO lop) {
        if (!isValidClass(lop)) {
            System.err.println("Dữ liệu lớp không hợp lệ: " + lop);
            return false;
        }
        boolean result = classDAO.themLop(lop);
        if (result) {
            System.out.println("Thêm lớp thành công: " + lop);
        } else {
            System.err.println("Thêm lớp thất bại: " + lop);
        }
        return result;
    }

    // Sửa thông tin lớp
    public boolean suaLop(ClassDTO lop) {
        if (!isValidClass(lop) || lop.getIDClass() <= 0) {
            System.err.println("Dữ liệu lớp không hợp lệ hoặc ID class không hợp lệ: " + lop);
            return false;
        }
        boolean result = classDAO.updateLop(lop);
        if (result) {
            System.out.println("Cập nhật lớp thành công: " + lop);
        } else {
            System.err.println("Cập nhật lớp thất bại: " + lop);
        }
        return result;
    }

    // Xóa lớp
    public boolean xoaLop(int idLop) {
        if (idLop <= 0) {
            System.err.println("ID lớp không hợp lệ: " + idLop);
            return false;
        }
        boolean result = classDAO.xoaLop(idLop);
        if (result) {
            System.out.println("Xóa lớp thành công với ID: " + idLop);
        } else {
            System.err.println("Xóa lớp thất bại với ID: " + idLop);
        }
        return result;
    }

    // Lấy danh sách tất cả các lớp
    public List<ClassDTO> layDanhSachLop() {
        List<ClassDTO> danhSachLop = classDAO.getAllClass();
        if (danhSachLop.isEmpty()) {
            System.err.println("Không có lớp nào trong danh sách.");
        } else {
            System.out.println("Lấy danh sách lớp thành công.");
        }
        return danhSachLop;
    }

    // Xác thực dữ liệu của lớp
    private boolean isValidClass(ClassDTO lop) {
        if (lop == null) {
            System.err.println("Lớp không được null.");
            return false;
        }
        if (lop.getNameClass() == null || lop.getNameClass().trim().isEmpty()) {
            System.err.println("Tên lớp không được để trống.");
            return false;
        }
        if (lop.getIDTeacher() <= 0) {
            System.err.println("ID giáo viên không hợp lệ: " + lop.getIDTeacher());
            return false;
        }
        return true;
    }
}