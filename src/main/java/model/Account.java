package model;

public class Account {

    //attributes
    private int id;
    private int balance;
    private Client client;
    private CreditCard creditCard;
    private Branch branch;


    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }


    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }



    //getters

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Branch getBranch() {
        return branch;
    }
}
