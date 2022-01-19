package service;
import model.Account;
import model.Client;
import model.CreditCard;
import repository.AccountRepo;
import repository.CreditCardRepo;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class CreditCardService {

    private static CreditCardRepo creditCardRepo;



    //add a new credit card
    public static boolean adNew(int accountID) throws SQLException, ParseException {

        CreditCard creditCard=new CreditCard();
        creditCard.createRandomCreditCard();
        return creditCardRepo.generate(creditCard.getCcNumber(),creditCard.getCVV2(), (Date) creditCard.getExpireDate(),creditCard.getPasscode(),accountID);

    }


    //remove a credit card
    public static boolean remove(int ccNumber) throws SQLException, ParseException {

        CreditCard cc=creditCardRepo.showInfo(ccNumber);
        if (cc==null){
            return false;
        }
        else return creditCardRepo.remove(ccNumber);
    }


    //change password of a credit card
    public static boolean changePassword(int newPassword,int ccNumber) throws SQLException, ParseException {

        CreditCard cc=creditCardRepo.showInfo(ccNumber);
        if (cc==null){
            return false;
        }
        else return creditCardRepo.changePassword(newPassword,ccNumber);
    }



    //check for password
    public static boolean checkPassWord(long ccNumber,int password) throws SQLException, ParseException {
        CreditCard cc = creditCardRepo.showInfo(ccNumber);

        return password == cc.getPasscode();
    }




    //show credit card info
    public static CreditCard showInfo(long ccNumber) throws SQLException, ParseException {
        CreditCard creditCard=creditCardRepo.showInfo(ccNumber);
        return creditCard;
    }



    //card to card service
    public static boolean cardToCard(long ccNumber,long destinationCCNumber,int amount,
                                     String description,int password,Date expireDate) throws SQLException, ParseException {

        boolean flag=false;
        CreditCard creditCard=showInfo(ccNumber);

        if (password==creditCard.getPasscode() && expireDate.compareTo(creditCard.getExpireDate())>0){
            AccountService.deduction(amount,ccNumber);
            AccountService.addition(amount,destinationCCNumber);
            TransactionService.createTransaction(ccNumber,destinationCCNumber,amount,description);
            flag=true;
        }
        return flag;
    }
}
