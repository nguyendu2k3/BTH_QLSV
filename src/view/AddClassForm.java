package view;

import dao.ClassDAO;
import model.ClassDTO;
import dao.TeacherCNDAO;
import model.TeacherCN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassForm extends JFrame {
    private JTextField txtIDclass, txtName, txtTeacherID;
    private JButton btnAdd;

    public AddClassForm() {
        setTitle("Add Class");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblID = new JLabel("ID Class:");
        lblID.setBounds(20, 20, 80, 25);
        add(lblID);
        txtIDclass = new JTextField();
        txtIDclass.setBounds(100, 20, 150, 25);
        add(txtIDclass);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 60, 80, 25);
        add(lblName);
        txtName = new JTextField();
        txtName.setBounds(100, 60, 150, 25);
        add(txtName);

        JLabel lblTeacherID = new JLabel("Teacher ID:");
        lblTeacherID.setBounds(20, 100, 80, 25);
        add(lblTeacherID);
        txtTeacherID = new JTextField();
        txtTeacherID.setBounds(100, 100, 150, 25);
        add(txtTeacherID);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 140, 80, 30);
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = txtName.getText();
                    int teacherId = Integer.parseInt(txtTeacherID.getText());

                    // First check if the teacher exists
                    TeacherCNDAO teacherDAO = new TeacherCNDAO();
                    TeacherCN teacher = teacherDAO.findTeacherById(teacherId);

                    if (teacher == null) {
                        JOptionPane.showMessageDialog(null, "Teacher ID does not exist!");
                        return;
                    }

                    ClassDTO lop = new ClassDTO(name, teacherId);
                    ClassDAO dao = new ClassDAO();
                    boolean result = dao.themLop(lop); // Modified to use only one argument

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Added successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Add failed!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddClassForm().setVisible(true));
    }
}