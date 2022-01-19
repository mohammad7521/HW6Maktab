package repository;
import connection.ConnectionProvider;
import model.Account;
import model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ClientRepo {


    public ClientRepo() throws SQLException {
        ConnectionProvider.setConnection();

    }

    //CRUD



    //create new client
    public boolean add(String firstName,String lastName,String address,char gender) throws SQLException {

        String insert="INSERT INTO client (default,?,?,?,?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setObject(4, Types.CHAR,gender);

        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove a client
    public boolean remove(int clientID) throws SQLException {
        String remove="DELETE FROM client WHERE clientID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,clientID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }




    //update info of a client
    public boolean update(int clientID,String firstName,String lastName,String address,char gender) throws SQLException {
        String update="UPDATE client SET firstName=?,lastName=?,address,=?,gender=? WHERE clientID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setString(4,String.valueOf(gender));
        preparedStatement.setInt(5,clientID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }



    //show client info
    public Client showInfo(int clientID) throws SQLException {
        String select="select * from client inner join Account A on client.clientid = A.clientID where client.clientid=?;";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(select);

        preparedStatement.setInt(1,clientID);

        ResultSet resultSet=preparedStatement.executeQuery();

        Client client=null;
        Account account;
        List<Account> accountList=new ArrayList<>();

        while(resultSet.next()) {

            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String gender = resultSet.getString(5);
            char charGender=gender.charAt(0);
            int accountID=resultSet.getInt(6);
            int balance=resultSet.getInt(7);

            account=new Account(accountID,balance);

            accountList.add(account);

            client = new Client(id, firstName, lastName, address,charGender,accountList);
        }
        preparedStatement.close();

        return client;
    }



    //
}
