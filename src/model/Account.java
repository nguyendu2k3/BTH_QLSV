package model;

public class Account {
    private String userName;
    private String passWord;
    private int ID;
    public Account(String userName, String passWord, int iD) {
        super();
        this.userName = userName;
        this.passWord = passWord;
        this.ID = iD;
    }
    public Account() {
        super();
    }


    public Account(int iD) {
        super();
        this.ID = iD;
    }
    public Account(String userName, String passWord) {
        super();
        this.userName = userName;
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
}
