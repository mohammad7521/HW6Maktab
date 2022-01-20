package repository;

import connection.ConnectionProvider;
import model.Account;
import model.Transaction;
import service.AccountService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepo {

    public TransactionRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }



    //add a transaction
    public boolean add(Date date, long ccNumber, long destinationCCNumber, long amount,String description) throws SQLException, ClassNotFoundException {

        String insert="INSERT INTO transaction (date,amount,ccnumber,ccnumberdestination,description) values(?,?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setDate(1,date);
        preparedStatement.setLong(2,amount);
        preparedStatement.setLong(3,ccNumber);
        preparedStatement.setLong(4,destinationCCNumber);
        preparedStatement.setString(5,description);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //transaction fee transaction
    public boolean addTransactionFee(Date date, long ccNumber, long amount,String description) throws SQLException, ClassNotFoundException {

        String insert="INSERT INTO transaction (date,amount,ccnumber,description) values(?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setDate(1,date);
        preparedStatement.setLong(2,amount);
        preparedStatement.setLong(3,ccNumber);
        preparedStatement.setString(4,description);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }


    //show transactions list based on creditCardNumber
    public List<Transaction> showList(int accountID) throws SQLException, ClassNotFoundException {

        Account account= AccountService.showInfo(accountID);
        long ccNumber=account.getCreditCardNumber();

        String showList="SELECT * FROM transaction where ccnumber=?";
        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showList);
        preparedStatement.setLong(1,ccNumber);

        ResultSet resultSet=preparedStatement.executeQuery();

        List<Transaction> transactionList=new ArrayList<>();

        while (resultSet.next()){
            Transaction transaction=new Transaction();
            int transactionID=resultSet.getInt(1);
            Date transactionDate=resultSet.getDate(2);
            long amount=resultSet.getLong(3);
            long from=resultSet.getLong(4);
            long to=resultSet.getLong(5);
            String description=resultSet.getString(6);

            transaction.setId(transactionID);
            transaction.setDate(transactionDate);
            transaction.setAmount(amount);
            transaction.setCreditCard(from);
            transaction.setDestinationCC(to);
            transaction.setDescription(description);
            transactionList.add(transaction);
        }
        return transactionList;
    }


    //show transaction based on date
    public List<Transaction> showList(int accountID,Date startDate) throws SQLException, ClassNotFoundException {

        Account account= AccountService.showInfo(accountID);
        long ccNumber=account.getCreditCardNumber();

        String showList="select * from transaction where ccNumber=(?) and date>=(?)";
        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showList);
        preparedStatement.setLong(1,ccNumber);
        preparedStatement.setDate(2,startDate);
        ResultSet resultSet=preparedStatement.executeQuery();

        List<Transaction> transactionList=new ArrayList<>();

        while (resultSet.next()){
            Transaction transaction=new Transaction();
            int transactionID=resultSet.getInt(1);
            Date transactionDate=resultSet.getDate(2);
            long amount=resultSet.getLong(3);
            long from=resultSet.getLong(4);
            long to=resultSet.getLong(5);
            String description=resultSet.getString(6);

            transaction.setId(transactionID);
            transaction.setDate(transactionDate);
            transaction.setAmount(amount);
            transaction.setCreditCard(from);
            transaction.setDestinationCC(to);
            transaction.setDescription(description);
            transactionList.add(transaction);
        }
        return transactionList;
    }

}
