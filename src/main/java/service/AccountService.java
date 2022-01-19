package service;

import model.Account;
import model.BranchBoss;
import repository.AccountRepo;
import java.sql.SQLException;

public class AccountService {

    private static AccountRepo accountRepo;


    //add a new account
    public boolean addNew(int clientID,int branchID,int initialDeposit) throws SQLException {

        return accountRepo.add(initialDeposit,clientID,branchID);
    }


    //remove an account
    public boolean remove(int accountID) throws SQLException {

        Account account=AccountRepo.showInfo(accountID);

        if(account==null){
            return false;
        }
        else return accountRepo.remove(accountID);
    }
}
