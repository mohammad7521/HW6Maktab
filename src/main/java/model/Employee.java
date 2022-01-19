package model;

public class Employee {

    //attributes
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private char gender;
    private Branch branch;
    private BranchBoss boss;


    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setBoss(BranchBoss boss) {
        this.boss = boss;
    }
}
