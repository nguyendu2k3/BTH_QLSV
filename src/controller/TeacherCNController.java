package controller;

import dao.TeacherCNDAO;
import model.TeacherCN;
import java.util.ArrayList;

public class TeacherCNController {
    private TeacherCNDAO teacherDAO = new TeacherCNDAO();

    public ArrayList<TeacherCN> getAllTeachers() {
        return teacherDAO.getAllTeachers();
    }

    public int addTeacher(TeacherCN t) {
        return teacherDAO.addTeacher(t);
    }

    public int updateTeacher(TeacherCN t) {
        return teacherDAO.updateTeacher(t);
    }

    public int deleteTeacher(int IDTeacher) {
        return teacherDAO.deleteTeacher(IDTeacher);
    }

    public TeacherCN findTeacherById(int IDTeacher) {
        return teacherDAO.findTeacherById(IDTeacher);
    }
}