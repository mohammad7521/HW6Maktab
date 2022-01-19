package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CreditCard {

    //attributes
    private long ccNumber;
    private int CVV2;
    private int passcode;
    private Account account;
    private Date expireDate;


    private static Date calculateExpiration() throws ParseException {

        Date returnDate=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String expirationString="00/00/0004";
        Date expiration=formatter.parse(expirationString);
        long sum=date.getTime()+expiration.getTime();
        Date sumDate=new Date(sum);
        returnDate=sumDate;

        return returnDate;
    }


    public void createRandomCreditCard() throws ParseException {
        long ccSmallest=1000_0000_0000_0000L;
        long ccBiggest=9999_9999_9999_9999L;
        long randomCC= ThreadLocalRandom.current().nextLong(ccSmallest,ccBiggest+1);
        this.ccNumber =randomCC;

        int passSmallest=0000;
        int passBiggest=9999;
        int randomPass=ThreadLocalRandom.current().nextInt(passSmallest,passBiggest+1);
        this.passcode=randomPass;

        int randomCvv2=ThreadLocalRandom.current().nextInt(passSmallest,passBiggest+1);
        this.CVV2=randomCvv2;

        this.expireDate=calculateExpiration();


    }


    //getters

    public long getCcNumber() {
        return ccNumber;
    }

    public int getCVV2() {
        return CVV2;
    }

    public int getPasscode() {
        return passcode;
    }

    public Account getAccount() {
        return account;
    }

    public Date getExpireDate() {
        return expireDate;
    }



    //setters

    public void setCcNumber(long ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void setCVV2(int CVV2) {
        this.CVV2 = CVV2;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
