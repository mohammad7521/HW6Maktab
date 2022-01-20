package model;

import java.util.List;

public class Client {

    //attributes
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private char gender;
    private List<Account> account;

    public Client(int id, String firstName, String lastName, String address, char gender, List<Account> account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.account = account;
    }

    public Client() {
    }

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

    public void setAccount(List<Account> account) {
        this.account = account;
    }


    //getters

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

    public char getGender() {
        return gender;
    }

    public List<Account> getAccount() {
        return account;
    }
}
