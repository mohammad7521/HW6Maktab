package repository;

import connection.ConnectionProvider;
import model.Account;
import model.BranchBoss;
import model.Client;

import java.sql.*;

public class AccountRepo {


    public AccountRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }


    //CRUD


    //add an account
    public boolean add(int balance,int clientID,int branchID) throws SQLException {

        String insert="INSERT INTO account (default,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setInt(1,balance);
        preparedStatement.setInt(2,clientID);
        preparedStatement.setInt(3,branchID);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove an account
    public boolean remove(int accountID) throws SQLException {

        String remove="DELETE FROM account WHERE accountID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,accountID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }




    //balance addition based on credit card number
    public boolean balanceAddition(int amount,long CCNumber) throws SQLException {
        String update="update account set balance=balance+(?) from creditCard where account.accountid=(\n" +
                "    select creditCard.accountID from creditCard where ccnumber=?);";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,amount);
        preparedStatement.setLong(2,CCNumber);
        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }




    //balance deduction based on credit card number
    public boolean balanceDeduction(int amount,long CCNumber) throws SQLException {
        String update="update account set balance=balance-(?) from creditCard where account.accountid=(\n" +
                "    select creditCard.accountID from creditCard where ccnumber=?);";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,amount);
        preparedStatement.setLong(2,CCNumber);
        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }






    //show account info based on account id
    public  Account showInfo(int accountID) throws SQLException {
        String showInfo="SELECT * FROM account where accountid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setInt(1,accountID);
        ResultSet resultSet=preparedStatement.executeQuery();

        Account account=null;

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            int balance=resultSet.getInt(2);


            account.setId(id);
            account.setBalance(balance);
        }

        return account;
    }



    //show account info based on credit card number

    public Account showInfoBasedOnCC(long creditCardNumber) throws SQLException {
        String showInfo="select * from account inner join account a on Account.accountID = a.accountid where a.accountID=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setLong(1,creditCardNumber);
        ResultSet resultSet=preparedStatement.executeQuery();

        Account account=null;

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            int balance=resultSet.getInt(2);


            account.setId(id);
            account.setBalance(balance);
        }

        return account;
    }

}
