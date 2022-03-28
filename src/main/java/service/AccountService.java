package service;

import model.Account;
import model.Branch;
import model.BranchBoss;
import repository.AccountRepo;

import java.sql.SQLException;
import java.text.ParseException;

public class AccountService {

    private static AccountRepo accountRepo;

    static {
        accountRepo = new AccountRepo();
    }


    //add a new account
    public static boolean addNew(int clientID, int branchID, long initialDeposit) {

        boolean checkFlag = false;
        Branch branch = null;
        branch = BranchService.showInfo(branchID);

        if (branch.getId() != branchID) {
            checkFlag = false;
        } else {
            Account account = accountRepo.add(initialDeposit, clientID, branchID);
            CreditCardService.addNew(account.getId());
            checkFlag = true;
        }
        return checkFlag;
    }


    //remove an account
    public static boolean remove(int accountID) {

        Account account = accountRepo.showInfo(accountID);

        if (account == null) {
            return false;
        } else return accountRepo.remove(accountID);
    }


    //balance addition based on CC
    public static boolean addition(long amount, long ccNumber) {
        return accountRepo.balanceAddition(amount, ccNumber);
    }


    //balance deduction based on CC
    public static boolean deduction(long amount, long ccNumber) {
        Account account = accountRepo.showInfoBasedOnCC(ccNumber);

        if (account.getBalance() > amount) {
            return accountRepo.balanceDeduction(amount, ccNumber);
        } else return false;
    }


    //balance addition based on accountID
    public static boolean addition(long amount, int accountID) {
        return accountRepo.balanceAddition(amount, accountID);
    }


    //balance deduction based on accountID
    public static boolean deduction(long amount, int accountID) {
        Account account = accountRepo.showInfo(accountID);

        if (account.getBalance() > amount) {
            return accountRepo.balanceDeduction(amount, accountID);
        } else return false;
    }


    //show account info
    public static Account showInfo(int accountID) {
        Account account = accountRepo.showInfo(accountID);
        return account;
    }
}
