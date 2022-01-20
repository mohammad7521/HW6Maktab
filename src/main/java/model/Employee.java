package model;

public class Employee {

    //attributes
    private int id;
    private String firstName;
    private String lastName;
    private String address;
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

    public void setBoss(BranchBoss boss) {
        this.boss = boss;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public BranchBoss getBoss() {
        return boss;
    }
}
