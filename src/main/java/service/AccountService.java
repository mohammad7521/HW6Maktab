package service;

import model.Account;
import model.BranchBoss;
import repository.AccountRepo;
import java.sql.SQLException;
import java.text.ParseException;

public class AccountService {

    private static AccountRepo accountRepo;


    //add a new account
    public static void addNew(int clientID,int branchID,int initialDeposit) throws SQLException, ParseException {

        Account account=accountRepo.add(initialDeposit,clientID,branchID);
        CreditCardService.addNew(account.getId());
    }



    //remove an account
    public static boolean remove(int accountID) throws SQLException {

        Account account=accountRepo.showInfo(accountID);

        if(account==null){
            return false;
        }
        else return accountRepo.remove(accountID);
    }



    //balance addition
    public static boolean addition(int amount,long ccNumber) throws SQLException {
        return accountRepo.balanceAddition(amount,ccNumber);
    }


    //balance deduction
    public static boolean deduction(int amount,long ccNumber) throws SQLException {
        Account account = accountRepo.showInfoBasedOnCC(ccNumber);

        if (account.getBalance() > amount) {
            return accountRepo.balanceDeduction(amount,ccNumber);
        }
        else return false;
    }

}
