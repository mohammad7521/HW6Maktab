package model;

public class Account {

    //attributes
    private int id;
    private long balance;
    private Client client=new Client();
    private CreditCard creditCard=new CreditCard();
    private Branch branch=new Branch();



    public Account(int id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account() {
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        creditCard.setCcNumber(creditCardNumber);
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setBranchID(int id){
        this.branch.setId(id);
    }

    public void setClientID(int id){
        this.client.setId(id);
    }


    //getters

    public int getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public long getCreditCardNumber() {
        return creditCard.getCcNumber();
    }

    public Branch getBranch() {
        return branch;
    }

    public int getBranchID(){
        return branch.getId();
    }

    public int getClientID(){
        return client.getId();
    }
}
