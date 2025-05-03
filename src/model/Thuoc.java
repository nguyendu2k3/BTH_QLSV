package model;

public class Thuoc {
    private int ID; // ID của sinh viên
    private int IDClass;    // ID của lớp

    // Constructor không tham số`
    public Thuoc() {
    }

    // Constructor đầy đủ tham số
    public Thuoc(int ID, int IDClass) {
        this.ID = ID;
        this.IDClass = IDClass;
    }

    // Getter và Setter cho IDSinhVien
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID ;
    }

    // Getter và Setter cho IDClass
    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Thuoc{" +
                "ID='" + ID + '\'' +
                ", IDClass='" + IDClass + '\'' +
                '}';
    }
}
