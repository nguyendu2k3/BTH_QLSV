package view;

import dao.SinhvienDAO;
import model.Sinhvien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentForm extends JFrame {
    private JTextField txtID, txtName, txtAge, txtAddress, txtIDClass;
    private JComboBox<String> cbGender;
    private JButton btnAdd;

    public AddStudentForm() {
        setTitle("Add Student");
        setSize(300, 340); // Increased height to fit all components
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ID Student
        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(20, 20, 80, 25);
        add(lblID);
        txtID = new JTextField();
        txtID.setBounds(100, 20, 150, 25);
        add(txtID);

        // ID Class
        JLabel lblIDClass = new JLabel("ID Class:");
        lblIDClass.setBounds(20, 60, 80, 25);
        add(lblIDClass);
        txtIDClass = new JTextField();
        txtIDClass.setBounds(100, 60, 150, 25);
        add(txtIDClass);

        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 100, 80, 25);
        add(lblName);
        txtName = new JTextField();
        txtName.setBounds(100, 100, 150, 25);
        add(txtName);

        // Age
        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(20, 140, 80, 25);
        add(lblAge);
        txtAge = new JTextField();
        txtAge.setBounds(100, 140, 150, 25);
        add(txtAge);

        // Gender
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(20, 180, 80, 25);
        add(lblGender);
        cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cbGender.setBounds(100, 180, 150, 25);
        add(cbGender);

        // Address
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(20, 220, 80, 25);
        add(lblAddress);
        txtAddress = new JTextField();
        txtAddress.setBounds(100, 220, 150, 25);
        add(txtAddress);

        // Add button
        btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 260, 80, 30);
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    int idclass = Integer.parseInt(txtIDClass.getText());
                    String name = txtName.getText();
                    int age = Integer.parseInt(txtAge.getText());
                    String gender = cbGender.getSelectedItem().toString();
                    String address = txtAddress.getText();

                    Sinhvien sv = new Sinhvien(id, name, age, gender, address, idclass);
                    SinhvienDAO dao = new SinhvienDAO();
                    int result = dao.themSinhVien(sv);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Added successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Add failed!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddStudentForm().setVisible(true));
    }
}