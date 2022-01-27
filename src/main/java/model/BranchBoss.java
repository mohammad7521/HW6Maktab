package model;

import java.util.Date;

public class BranchBoss {

    //attributes
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private Branch branch=new Branch();
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setBranchID(int branchID){this.branch.setId(branchID);}

    //getters

    public int getId() {
        return id;
    }

    public int getBranchID(){
        return this.branch.getId();
    }
}
