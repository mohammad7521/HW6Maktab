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

}
