package model;

public class ClassDTO {
    private int IDClass;
    private String NameClass;
    private int IDTeacher;
    private String teacherCNName;

    public ClassDTO() {
    }

    public String getTeacherCNName() {
        return teacherCNName;
    }

    public void setTeacherCNName(String teacherCNName) {
        this.teacherCNName = teacherCNName;
    }

    public ClassDTO(String NameClass, int IDTeacher) {
        this.NameClass = NameClass;
        this.IDTeacher = IDTeacher;
    }
    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    public String getNameClass() {
        return NameClass;
    }

    public void setNameClass(String NameClass) {
        this.NameClass = NameClass;
    }

    public int getIDTeacher() {
        return IDTeacher;
    }
    public void setIDTeacher(int IDTeacher) {
        this.IDTeacher = IDTeacher;
    }
    @Override
    public String toString() {
        return "ClassDTO{" +
                "IDClass='" + IDClass + '\'' +
                ", NameClass='" + NameClass + '\'' +
                ", IDTeacher='" + IDTeacher + '\'' +
                '}';
    }
}
