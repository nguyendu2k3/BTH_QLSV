package model;

public class Hoc {
    private int ID; // Foreign key to SinhVien
    private int IDSubject; // Foreign key to Subject
    private float score;

    public Hoc(int ID, int IDSubject, float score) {
        this.ID = ID;
        this.IDSubject = IDSubject ;
        this.score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDSubject() {
        return IDSubject;
    }

    public void setSubjectId(int IDSubject) {
        this.IDSubject= IDSubject;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
