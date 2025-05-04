package view;

import controller.ClassController;
import controller.SinhvienController;
import model.ClassDTO;
import model.Sinhvien;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClassStudentsForm extends JFrame {
    private JTable tableClasses, tableStudents;
    private DefaultTableModel classModel, studentModel;
    private JButton btnAdd, btnRefresh;
    private ClassController classController;
    private SinhvienController sinhvienController;
    private int selectedClassId;

    public ClassStudentsForm() {
        classController = new ClassController();
        sinhvienController = new SinhvienController();
        initComponents();
        loadClasses();
    }

    private void initComponents() {
        setTitle("Class ⇄ Students");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Added "Teacher CN" column
        classModel   = new DefaultTableModel(new String[] { "Class ID", "Class Name", "Teacher CN" }, 0);
        studentModel = new DefaultTableModel(new String[] { "ID", "Name", "Age", "Gender", "Address" }, 0);

        tableClasses  = new JTable(classModel);
        tableStudents = new JTable(studentModel);
        tableClasses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableClasses.getSelectionModel().addListSelectionListener((ListSelectionListener) e -> {
            int row = tableClasses.getSelectedRow();
            if (row >= 0) {
                selectedClassId = (int) classModel.getValueAt(row, 0);
                loadStudents(selectedClassId);
            }
        });

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(tableClasses), new JScrollPane(tableStudents));
        split.setDividerLocation(300);

        btnAdd     = new JButton("Add to Class");
        btnRefresh = new JButton("Refresh");

        btnAdd.addActionListener(e -> addStudentsToClass());
        btnRefresh.addActionListener(e -> {
            loadClasses();
            studentModel.setRowCount(0);
        });

        JPanel control = new JPanel();
        control.add(btnAdd);
        control.add(btnRefresh);

        setLayout(new BorderLayout());
        add(split, BorderLayout.CENTER);
        add(control, BorderLayout.SOUTH);
    }

    private void loadClasses() {
        classModel.setRowCount(0);
        List<ClassDTO> classes = classController.layDanhSachLop();
        for (ClassDTO c : classes) {
            // Assumes ClassDTO has getTeacherCNName() method
            classModel.addRow(new Object[] {
                    c.getIDClass(),
                    c.getNameClass(),
                    c.getTeacherCNName()
            });
        }
    }

    private void loadStudents(int idLop) {
        studentModel.setRowCount(0);
        List<Sinhvien> list = sinhvienController.layDanhSachSinhVienTheoIDLop(idLop);
        for (Sinhvien sv : list) {
            studentModel.addRow(new Object[]{
                    sv.getID(), sv.getName(), sv.getAge(), sv.getGender(), sv.getAddress()
            });
        }
    }

    private void addStudentsToClass() {
        int[] rows = tableStudents.getSelectedRows();
        if (selectedClassId == 0 || rows.length == 0) {
            JOptionPane.showMessageDialog(this, "Select a class and at least one student");
            return;
        }

        boolean anySuccess = false;
        for (int r : rows) {
            int sid = (int) studentModel.getValueAt(r, 0);
            anySuccess |= sinhvienController.themSinhVienVaoLop(sid, selectedClassId);
        }

        String msg = anySuccess ? "Added to class successfully" : "No students were added";
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClassStudentsForm().setVisible(true));
    }
}