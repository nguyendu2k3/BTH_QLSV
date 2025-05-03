package model;

public class TeacherCN {
    private int IDTeacher;
    private String Name;
    private int Age;
    private String Gender ;

    // Constructor không tham số
    public TeacherCN() {
    }

    // Constructor đầy đủ tham số
    public TeacherCN (int IDTeacher, String Name, int Age, String Gender) {
        this.IDTeacher = IDTeacher;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
    }

    // Getter và Setter
    public int getIDTeacher() {
        return IDTeacher;
    }

    public void setIDTeacher(int IDTeacher) {
        this.IDTeacher = IDTeacher;

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "TeacherCN{" +
                "IDTeacher='" + IDTeacher + '\'' +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Gender='" + Gender + '\'' +
                '}';
    }
}
