package view;

import controller.ClassController;
import controller.SinhvienController;
import model.ClassDTO;
import model.Sinhvien;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LopHocThongKeForm extends JFrame {
    private JTable tableLop;
    private JTable tableSinhVien;
    private DefaultTableModel modelLop;
    private DefaultTableModel modelSinhVien;
    private ClassController classController;
    private SinhvienController sinhvienController;

    public LopHocThongKeForm() {
        // Tiêu đề cửa sổ
        setTitle("Thống kê lớp học & Điểm danh sinh viên");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        classController = new ClassController();
        sinhvienController = new SinhvienController();

        // Bảng lớp học: ID, Tên lớp, Số sinh viên
        modelLop = new DefaultTableModel(new Object[]{"ID", "Tên lớp", "Số sinh viên"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableLop = new JTable(modelLop);
        JScrollPane scrollLop = new JScrollPane(tableLop);

        // Bảng sinh viên: ID, Họ tên, Tuổi, Giới tính, Địa chỉ, Có mặt
        modelSinhVien = new DefaultTableModel(new Object[]{"ID", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "Có mặt"}, 0) {
            public Class<?> getColumnClass(int column) {
                if (column == 5) return Boolean.class; // Cột điểm danh là checkbox
                return String.class;
            }
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Chỉ cho phép sửa cột điểm danh
            }
        };
        tableSinhVien = new JTable(modelSinhVien);
        JScrollPane scrollSinhVien = new JScrollPane(tableSinhVien);

        // Chia đôi giao diện: trái là lớp, phải là sinh viên
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollLop, scrollSinhVien);
        splitPane.setDividerLocation(350);

        add(splitPane, BorderLayout.CENTER);

        // Tải dữ liệu lớp học lên bảng
        loadLopHoc();

        // Sự kiện chọn lớp: hiển thị danh sách sinh viên của lớp đó
        tableLop.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tableLop.getSelectedRow();
                if (selectedRow >= 0) {
                    String tenLop = modelLop.getValueAt(selectedRow, 1).toString();
                    loadSinhVienTheoLop(tenLop);
                }
            }
        });
    }

    // Hàm tải danh sách lớp và số sinh viên từng lớp
    private void loadLopHoc() {
        modelLop.setRowCount(0);
        List<ClassDTO> dsLop = classController.layDanhSachLop();
        for (ClassDTO lop : dsLop) {
            List<Sinhvien> dsSV = sinhvienController.layDanhSachSinhVienTheoTenLop(lop.getNameClass());
            int soSV = dsSV != null ? dsSV.size() : 0;
            modelLop.addRow(new Object[]{lop.getIDClass(), lop.getNameClass(), soSV});
        }
    }

    // Hàm tải danh sách sinh viên theo tên lớp
    private void loadSinhVienTheoLop(String tenLop) {
        modelSinhVien.setRowCount(0);
        List<Sinhvien> dsSV = sinhvienController.layDanhSachSinhVienTheoTenLop(tenLop);
        if (dsSV != null) {
            for (Sinhvien sv : dsSV) {
                modelSinhVien.addRow(new Object[]{
                        sv.getID(), sv.getName(), sv.getAge(), sv.getGender(), sv.getAddress(), false
                });
            }
        }
    }

    // Hàm main để chạy form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LopHocThongKeForm().setVisible(true));
    }
}