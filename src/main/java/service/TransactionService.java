package service;

import repository.AccountRepo;
import repository.CreditCardRepo;
import repository.TransactionRepo;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

public class TransactionService {

        private static TransactionRepo transactionRepo;
        private static AccountRepo accountRepo;

        //add a new transaction
    public static void createTransaction (long ccNumber, long destinationCCNumber,int amount,String description) throws SQLException, ParseException {

            Date date=new Date();
            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
            Time sqlTime=new Time(date.getTime());
            transactionRepo.add(sqlDate,sqlTime,ccNumber,destinationCCNumber,amount,description);

            //transaction cost
            transactionRepo.add(sqlDate,sqlTime,ccNumber,1,6000,"transaction cost");
    }

}
