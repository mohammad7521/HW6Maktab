package model;

import java.sql.Time;
import java.util.Date;

public class Transaction {

    //attributes
    private int id;
    private Date date;
    private Time time;
    private long amount;
    private CreditCard creditCard;
    private CreditCard destinationCC;

}
