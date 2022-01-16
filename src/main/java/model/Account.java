package model;

public class Account {

    //attributes
    private int id;
    private int balance;
    private Client client;
    private CreditCard creditCard;
    private Branch branch;


    //constructors


    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}
