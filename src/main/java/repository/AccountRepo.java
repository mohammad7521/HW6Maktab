package repository;

import connection.ConnectionProvider;
import model.Account;
import model.BranchBoss;
import model.Client;

import java.sql.*;

public class AccountRepo {


    public AccountRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }


    //CRUD


    //add an account
    public Account add (int initialDeposit,int clientID,int branchID) throws SQLException, ClassNotFoundException {

        String insert="INSERT INTO account (balance,clientid,branchid) VALUES(?,?,?) RETURNING accountid";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        Account account=new Account();
        account.setBalance(initialDeposit);
        account.setBranchID(branchID);
        account.setClientID(clientID);

        preparedStatement.setInt(1,initialDeposit);
        preparedStatement.setInt(2,clientID);
        preparedStatement.setInt(3,branchID);


        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next() && resultSet!=null){
            account.setId(resultSet.getInt(1));
        }

        preparedStatement.close();
        return account;

    }



    //remove an account
    public boolean remove(int accountID) throws SQLException, ClassNotFoundException {

        String remove="DELETE FROM account WHERE accountID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,accountID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }




    //balance addition based on credit card number
    public boolean balanceAddition(int amount,long CCNumber) throws SQLException, ClassNotFoundException {
        String update="update account set balance=balance+(?) from creditCard where account.accountid=(\n" +
                "    select creditcard.accountID from creditcard where ccNumber=(?))";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,amount);
        preparedStatement.setLong(2,CCNumber);
        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }




    //balance deduction based on credit card number
    public boolean balanceDeduction(int amount,long CCNumber) throws SQLException, ClassNotFoundException {
        String update="update account set balance=balance-(?) from creditCard where account.accountid=(\n" +
                "    select creditcard.accountID from creditcard where ccNumber=(?))";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,amount+6000);
        preparedStatement.setLong(2,CCNumber);
        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }






    //show account info based on account id
    public  Account showInfo(int accountID) throws SQLException, ClassNotFoundException {
        String showInfo="SELECT * FROM account inner join creditcard on account.accountID=creditCard.accountID where Account.accountID=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setInt(1,accountID);
        ResultSet resultSet=preparedStatement.executeQuery();

        Account account=new Account();

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            int balance=resultSet.getInt(2);
            int clientID=resultSet.getInt(3);
            int branchID=resultSet.getInt(4);
            long creditCardNumber=resultSet.getLong(5);


            account.setId(id);
            account.setBalance(balance);
            account.setClientID(clientID);
            account.setBranchID(branchID);
            account.setCreditCardNumber(creditCardNumber);
        }

        return account;
    }



    //show account info based on credit card number
    public Account showInfoBasedOnCC(long creditCardNumber) throws SQLException, ClassNotFoundException {
        String showInfo="select * from account inner join creditcard on account.accountid=creditcard.accountID where ccNumber=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setLong(1,creditCardNumber);
        ResultSet resultSet=preparedStatement.executeQuery();

        Account account=new Account();

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            int balance=resultSet.getInt(2);


            account.setId(id);
            account.setBalance(balance);
        }

        return account;
    }

}
