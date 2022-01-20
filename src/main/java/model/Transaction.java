package model;

import java.sql.Time;
import java.util.Date;

public class Transaction {

    //attributes
    private int id;
    private java.sql.Date date;
    private int amount;
    private long creditCard;
    private long destinationCC;
    private String description;



    //getters
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }

    public long getCreditCard() {
        return creditCard;
    }

    public long getDestinationCC() {
        return destinationCC;
    }

    public String getDescription() {
        return description;
    }

    //setters


    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCreditCard(long creditCard) {
        this.creditCard = creditCard;
    }

    public void setDestinationCC(long destinationCC) {
        this.destinationCC = destinationCC;
    }


    //toString

    @Override
    public String toString() {
        return
                "transaction id:" + id +
                ", date:" + date +
                ", amount:" + amount +
                ", creditCard:" + creditCard +
                ", destinationCC:" + destinationCC +
                ", description:" + description;
    }
}
