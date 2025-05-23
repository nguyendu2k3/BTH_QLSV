package model;

public class Sinhvien {
    private int ID;
    private String Name;
    private int Age;
    private String Gender;
    private String Address;
    private int IDClass; // Thêm trường IDClass

    public Sinhvien() {

    }

    public Sinhvien(int ID, String Name, int Age, String Gender, String Address, int IDClass) {
        this.ID = ID;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.Address = Address;
        this.IDClass = IDClass;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    @Override
    public String toString() {
        return "Sinhvien{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Gender='" + Gender + '\'' +
                ", Address='" + Address + '\'' +
                ", IDClass=" + IDClass +
                '}';
    }
}