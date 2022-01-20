package model;

import java.util.Date;

public class BranchBoss {

    //attributes
    private int id;
    private String firstName;
    private String lastName;
    private char gender;
    private String address;
    private Branch branch;
    private Employee[] employees;



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

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
