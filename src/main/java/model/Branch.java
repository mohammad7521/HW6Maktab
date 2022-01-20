package model;

public class Branch {

    //attributes
    private int id;
    private String name;
    private String address;
    private BranchBoss boss;
    private Employee[] employees;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BranchBoss getBoss() {
        return boss;
    }

    public Employee[] getEmployees() {
        return employees;
    }
}
