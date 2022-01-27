package service;

import model.Transaction;
import repository.AccountRepo;
import repository.CreditCardRepo;
import repository.TransactionRepo;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TransactionService {

        private static TransactionRepo transactionRepo;
        private static AccountRepo accountRepo;

    static {
        try {
            transactionRepo = new TransactionRepo();
            accountRepo = new AccountRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add a new transaction
    public static boolean createTransaction (long ccNumber, long destinationCCNumber,long amount,String description){

            Date date=new Date();
            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
            boolean checkTransaction=transactionRepo.add(sqlDate,ccNumber,destinationCCNumber,amount,description);

            //transaction cost
            boolean checkTransactionCost=transactionRepo.addTransactionFee(sqlDate,ccNumber,6000,"transaction cost");
            return checkTransactionCost && checkTransaction;
    }


    //show transaction list
    public static List<Transaction> showTransactionList(int accountID)  {

        List<Transaction> transactionList=transactionRepo.showList(accountID);

        return transactionList;
    }


    //show transaction based on a date
    public static List<Transaction> showTransactionList(int accountID, java.sql.Date startDate)  {

        return transactionRepo.showList(accountID,startDate);
    }
}
