package view;

import dao.SinhvienDAO;
import model.Sinhvien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SinhVienForm extends JFrame {
    private JTable tableSinhVien;
    private DefaultTableModel tableModel;
    private JTextField txtID, txtName, txtAge, txtAddress;
    private JComboBox<String> cbGender;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnRefresh;
    private SinhvienDAO sinhvienDAO;

    public SinhVienForm() {
        sinhvienDAO = new SinhvienDAO();
        initComponents();
        loadDataToTable();
    }

    private void initComponents() {
        // Thiết lập cơ bản cho form
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel chứa thông tin sinh viên
        JPanel panelInfo = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));

        panelInfo.add(new JLabel("ID:"));
        txtID = new JTextField();
        panelInfo.add(txtID);

        panelInfo.add(new JLabel("Họ tên:"));
        txtName = new JTextField();
        panelInfo.add(txtName);

        panelInfo.add(new JLabel("Tuổi:"));
        txtAge = new JTextField();
        panelInfo.add(txtAge);

        panelInfo.add(new JLabel("Giới tính:"));
        cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        panelInfo.add(cbGender);

        panelInfo.add(new JLabel("Địa chỉ:"));
        txtAddress = new JTextField();
        panelInfo.add(txtAddress);

        // Panel chứa các nút chức năng
        JPanel panelButtons = new JPanel(new FlowLayout());

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm kiếm");
        btnRefresh = new JButton("Làm mới");

        panelButtons.add(btnThem);
        panelButtons.add(btnSua);
        panelButtons.add(btnXoa);
        panelButtons.add(btnTimKiem);
        panelButtons.add(btnRefresh);

        // Tạo bảng hiển thị danh sách sinh viên
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ tên");
        tableModel.addColumn("Tuổi");
        tableModel.addColumn("Giới tính");
        tableModel.addColumn("Địa chỉ");

        tableSinhVien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableSinhVien);

        // Thêm các component vào JFrame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelInfo, BorderLayout.CENTER);
        topPanel.add(panelButtons, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Xử lý sự kiện cho các nút
        addEventListeners();
    }

    private void addEventListeners() {
        // Xử lý sự kiện khi click vào hàng trong bảng
        tableSinhVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableSinhVien.getSelectedRow();
                if (selectedRow >= 0) {
                    txtID.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtAge.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    cbGender.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
                    txtAddress.setText(tableModel.getValueAt(selectedRow, 4).toString());

                    // Disable ID field khi đang sửa
                    txtID.setEditable(false);
                }
            }
        });

        // Nút Thêm sinh viên
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    String name = txtName.getText();
                    int age = Integer.parseInt(txtAge.getText());
                    String gender = cbGender.getSelectedItem().toString();
                    String address = txtAddress.getText();

                    Sinhvien sv = new Sinhvien(id, name, age, gender, address);
                    int result = sinhvienDAO.themSinhVien(sv);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Thêm sinh viên thành công!");
                        loadDataToTable();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm sinh viên thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID và Tuổi phải là số!");
                }
            }
        });

        // Nút Sửa sinh viên
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    String name = txtName.getText();
                    int age = Integer.parseInt(txtAge.getText());
                    String gender = cbGender.getSelectedItem().toString();
                    String address = txtAddress.getText();

                    Sinhvien sv = new Sinhvien(id, name, age, gender, address);
                    int result = sinhvienDAO.updateSinhVien(sv);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Cập nhật sinh viên thành công!");
                        loadDataToTable();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật sinh viên thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID và Tuổi phải là số!");
                }
            }
        });

        // Nút Xóa sinh viên
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = tableSinhVien.getSelectedRow();
                    if (selectedRow >= 0) {
                        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Bạn có chắc muốn xóa sinh viên này?",
                                "Xác nhận xóa",
                                JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            int result = sinhvienDAO.xoaSinhVien(id);
                            if (result > 0) {
                                JOptionPane.showMessageDialog(null, "Xóa sinh viên thành công!");
                                loadDataToTable();
                                clearFields();
                            } else {
                                JOptionPane.showMessageDialog(null, "Xóa sinh viên thất bại!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn sinh viên cần xóa!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + ex.getMessage());
                }
            }
        });

        // Nút Tìm kiếm sinh viên
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idStr = JOptionPane.showInputDialog("Nhập ID sinh viên cần tìm:");
                    if (idStr != null && !idStr.trim().isEmpty()) {
                        int id = Integer.parseInt(idStr);
                        Sinhvien sv = sinhvienDAO.timSinhVienTheoID(id);

                        if (sv != null) {
                            tableModel.setRowCount(0);
                            Object[] row = {sv.getID(), sv.getName(), sv.getAge(), sv.getGender(), sv.getAddress()};
                            tableModel.addRow(row);

                            // Hiển thị thông tin tìm được trên form
                            txtID.setText(String.valueOf(sv.getID()));
                            txtName.setText(sv.getName());
                            txtAge.setText(String.valueOf(sv.getAge()));
                            cbGender.setSelectedItem(sv.getGender());
                            txtAddress.setText(sv.getAddress());
                            txtID.setEditable(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên với ID: " + id);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID phải là số!");
                }
            }
        });

        // Nút Làm mới
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataToTable();
                clearFields();
            }
        });
    }

    // Hàm load dữ liệu từ DAO lên bảng
    private void loadDataToTable() {
        tableModel.setRowCount(0);
        ArrayList<Sinhvien> danhSachSV = sinhvienDAO.layDanhSachSinhVien();

        for (Sinhvien sv : danhSachSV) {
            Object[] row = {sv.getID(), sv.getName(), sv.getAge(), sv.getGender(), sv.getAddress()};
            tableModel.addRow(row);
        }
    }

    // Hàm xóa trắng các trường nhập liệu
    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        cbGender.setSelectedIndex(0);
        txtAddress.setText("");
        txtID.setEditable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SinhVienForm().setVisible(true);
            }
        });
    }
}