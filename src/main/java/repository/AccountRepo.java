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
    public Account add (long initialDeposit,int clientID,int branchID){

        String insert="INSERT INTO account (balance,clientid,branchid) VALUES(?,?,?) RETURNING accountid";

        Account account=new Account();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);


            account.setBalance(initialDeposit);
            account.setBranchID(branchID);
            account.setClientID(clientID);

            preparedStatement.setLong(1,initialDeposit);
            preparedStatement.setInt(2,clientID);
            preparedStatement.setInt(3,branchID);


            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next() && resultSet!=null){
                account.setId(resultSet.getInt(1));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return account;
    }



    //remove an account
    public boolean remove(int accountID) {

        String remove="DELETE FROM account WHERE accountID=?";

        int removeCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(remove);
            preparedStatement.setInt(1,accountID);

            removeCheck=preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return removeCheck >0;
    }




    //balance addition based on credit card number
    public boolean balanceAddition(long amount,long CCNumber) {
        String update="update account set balance=balance+(?) from creditCard where account.accountid=(\n" +
                "    select creditcard.accountID from creditcard where ccNumber=(?))";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);
            preparedStatement.setLong(1,amount);
            preparedStatement.setLong(2,CCNumber);
            updateCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return updateCheck >0;
    }




    //balance deduction based on credit card number
    public boolean balanceDeduction(long amount,long CCNumber) {

        String update="update account set balance=balance-(?) from creditCard where account.accountid=(\n" +
                "    select creditcard.accountID from creditcard where ccNumber=(?))";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);
            preparedStatement.setLong(1,amount+6000);
            preparedStatement.setLong(2,CCNumber);
            updateCheck=preparedStatement.executeUpdate();

            preparedStatement.close();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return updateCheck >0;
    }



    //balance addition based on account id
    public boolean balanceAddition(long amount,int accountID) {
        String update="update account set balance=balance+(?) where accountid=(?)";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);
            preparedStatement.setLong(1,amount);
            preparedStatement.setLong(2,accountID);
            updateCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return updateCheck >0;
    }




    //balance deduction based on account id
    public boolean balanceDeduction(long amount,int accountID) {
        String update="update account set balance=balance-(?) where accountid=(?)";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);
            preparedStatement.setLong(1,amount);
            preparedStatement.setLong(2,accountID);
            updateCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return updateCheck >0;
    }






    //show account info based on account id
    public  Account showInfo(int accountID) {

        String showInfo="SELECT * FROM account inner join creditcard on account.accountID=creditCard.accountID where Account.accountID=(?)";

        Account account=new Account();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,accountID);
            ResultSet resultSet=preparedStatement.executeQuery();



            while (resultSet.next()){
                int id=resultSet.getInt(1);
                long balance=resultSet.getLong(2);
                int clientID=resultSet.getInt(3);
                int branchID=resultSet.getInt(4);
                long creditCardNumber=resultSet.getLong(5);


                account.setId(id);
                account.setBalance(balance);
                account.setClientID(clientID);
                account.setBranchID(branchID);
                account.setCreditCardNumber(creditCardNumber);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return account;
    }



    //show account info based on credit card number
    public Account showInfoBasedOnCC(long creditCardNumber) {
        String showInfo="select * from account inner join creditcard on account.accountid=creditcard.accountID where ccNumber=(?)";

        Account account=new Account();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setLong(1,creditCardNumber);
            ResultSet resultSet=preparedStatement.executeQuery();



            while (resultSet.next()){
                int id=resultSet.getInt(1);
                long balance=resultSet.getLong(2);


                account.setId(id);
                account.setBalance(balance);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return account;
    }

}
