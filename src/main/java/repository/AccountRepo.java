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




    //update balance of an account
    public boolean updateBalance(int balance) throws SQLException {
        String update="UPDATE account SET balance=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,balance);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }






    //show account info
    public static Account showInfo(int accountID) throws SQLException {
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


}
