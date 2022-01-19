package service;
import model.Client;
import model.CreditCard;
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


}
