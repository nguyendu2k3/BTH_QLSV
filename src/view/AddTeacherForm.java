package view;

import controller.TeacherCNController;
import model.TeacherCN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherForm extends JFrame {
    private JTextField txtID, txtName, txtAge, txtGender;
    private JButton btnAdd;

    public AddTeacherForm() {
        setTitle("Add Teacher");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(20, 20, 80, 25);
        add(lblID);
        txtID = new JTextField();
        txtID.setBounds(100, 20, 150, 25);
        add(txtID);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 60, 80, 25);
        add(lblName);
        txtName = new JTextField();
        txtName.setBounds(100, 60, 150, 25);
        add(txtName);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(20, 100, 80, 25);
        add(lblAge);
        txtAge = new JTextField();
        txtAge.setBounds(100, 100, 150, 25);
        add(txtAge);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(20, 140, 80, 25);
        add(lblGender);
        txtGender = new JTextField();
        txtGender.setBounds(100, 140, 150, 25);
        add(txtGender);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 180, 80, 30);
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    String name = txtName.getText();
                    int age = Integer.parseInt(txtAge.getText());
                    String gender = txtGender.getText();
                    TeacherCN teacher = new TeacherCN(id, name, age, gender);
                    TeacherCNController controller = new TeacherCNController();
                    int result = controller.addTeacher(teacher);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Added successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Add failed!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddTeacherForm().setVisible(true));
    }
}