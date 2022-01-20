package service;

import model.Account;
import repository.AccountRepo;

import java.sql.SQLException;
import java.text.ParseException;

public class AccountService {

    private static AccountRepo accountRepo;

    static {
        try {
            accountRepo = new AccountRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add a new account
    public static boolean addNew(int clientID,int branchID,long initialDeposit) throws SQLException, ParseException, ClassNotFoundException {

        Account account=accountRepo.add(initialDeposit,clientID,branchID);
        return CreditCardService.addNew(account.getId());
    }



    //remove an account
    public static boolean remove(int accountID) throws SQLException, ClassNotFoundException {

        Account account=accountRepo.showInfo(accountID);

        if(account==null){
            return false;
        }
        else return accountRepo.remove(accountID);
    }



    //balance addition based on CC
    public static boolean addition(long amount,long ccNumber) throws SQLException, ClassNotFoundException {
        return accountRepo.balanceAddition(amount,ccNumber);
    }



    //balance deduction based on CC
    public static boolean deduction(long amount,long ccNumber) throws SQLException, ClassNotFoundException {
        Account account = accountRepo.showInfoBasedOnCC(ccNumber);

        if (account.getBalance() > amount) {
            return accountRepo.balanceDeduction(amount,ccNumber);
        }
        else return false;
    }


    //balance addition based on accountID
    public static boolean addition(long amount,int accountID) throws SQLException, ClassNotFoundException {
        return accountRepo.balanceAddition(amount,accountID);
    }



    //balance deduction based on accountID
    public static boolean deduction(long amount,int accountID) throws SQLException, ClassNotFoundException {
        Account account = accountRepo.showInfo(accountID);

        if (account.getBalance() > amount) {
            return accountRepo.balanceDeduction(amount,accountID);
        }
        else return false;
    }




    //show account info
    public static Account showInfo(int accountID) throws SQLException, ClassNotFoundException {
        Account account=accountRepo.showInfo(accountID);
        return account;
    }
}
