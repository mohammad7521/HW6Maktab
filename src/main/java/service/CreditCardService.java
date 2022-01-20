package service;

import model.Account;
import model.CreditCard;
import repository.CreditCardRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class CreditCardService {

    private static CreditCardRepo creditCardRepo;

    static {
        try {
            creditCardRepo = new CreditCardRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add a new credit card
    public static boolean addNew(int accountID) throws SQLException, ParseException, ClassNotFoundException {

        CreditCard creditCard=new CreditCard();
        creditCard.createRandomCreditCard();
        return creditCardRepo.generate(creditCard.getCcNumber(),creditCard.getCVV2(), (Date) creditCard.getExpireDate(),creditCard.getPasscode(),accountID);

    }


    //remove a credit card
    public static boolean remove(long ccNumber) throws SQLException, ParseException, ClassNotFoundException {

        return creditCardRepo.remove(ccNumber);
    }


    //change password of a credit card
    public static boolean changeCCPassword(int newPassword,int oldPassword,long ccNumber) throws SQLException, ParseException, ClassNotFoundException {

        boolean flag=false;
        CreditCard creditCard=showInfo(ccNumber);
        int password=creditCard.getPasscode();

        if (password==oldPassword) {
            creditCardRepo.changePassword(newPassword, ccNumber);
            flag=true;
        }
        return flag;
    }





    //show credit card info
    public static CreditCard showInfo(long ccNumber) throws SQLException, ParseException, ClassNotFoundException {
        CreditCard creditCard=creditCardRepo.showInfo(ccNumber);
        return creditCard;
    }




    //credit card password check
    public static boolean passwordCheck(int password,int accountID) throws SQLException, ClassNotFoundException, ParseException {
        Account account=AccountService.showInfo(accountID);
        long ccNumber=account.getCreditCardNumber();
        CreditCard creditCard=showInfo(ccNumber);
        return creditCard.getPasscode()==password;
    }




    //card to card service
    public static void cardToCard (long ccNumber,long destinationCCNumber,long amount,
                                     String description,int password,int cvv2) throws SQLException, ParseException, ClassNotFoundException {

        CreditCard creditCard = showInfo(ccNumber);
        java.util.Date date = new java.util.Date();
        Date now = new Date(date.getTime());
        int wrongEntries = creditCard.getWrongPasswordEntries();
        if (wrongEntries > 0) {
            System.out.println("you have entered your password wrong for " + wrongEntries+" times");
        }
        if (wrongEntries < 3) {
            if (password == creditCard.getPasscode()) {
                if (creditCard.getExpireDate().compareTo(now) > 0 && cvv2 == creditCard.getCVV2()) {
                    boolean deductionCheck = AccountService.deduction(amount, ccNumber);
                    boolean additionCheck = AccountService.addition(amount, destinationCCNumber);

                    if (deductionCheck) {
                        if (additionCheck) {
                            if (TransactionService.createTransaction(ccNumber, destinationCCNumber, amount, description)) {
                                System.out.println("transaction successful! ");
                            }
                        }
                    } else System.out.println("not enough balance! ");
                } else System.out.println("wrong cvv2 or card is expired!");
            } else {
                System.out.println("wrong password!");
                System.out.println("warning! wrong password entries: " + wrongPasswordEntry(ccNumber));

            }
        } else System.out.println("sorry! too many wrong passwords! ");
    }



    //entering wrong password
    public static int wrongPasswordEntry(long ccNumber) throws SQLException, ClassNotFoundException {

        return creditCardRepo.wrongPasswordEntry(ccNumber);
    }
}
