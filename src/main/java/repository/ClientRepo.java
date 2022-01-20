package repository;
import connection.ConnectionProvider;
import model.Account;
import model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepo {


    public ClientRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();

    }

    //CRUD



    //create new client
    public Client add(String firstName,String lastName,String address) throws SQLException, ClassNotFoundException {

        String insert="INSERT INTO client (firstname,lastname,address) VALUES (?,?,?) RETURNING clientid";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(insert);

        Client client=new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAddress(address);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);


        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next() && resultSet!=null){
            client.setId(resultSet.getInt(1));
        }
        return client;
    }



    //remove a client
    public boolean remove(int clientID) throws SQLException, ClassNotFoundException {
        String remove="DELETE FROM client WHERE clientID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,clientID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }




    //update info of a client
    public boolean update(int clientID,String firstName,String lastName,String address) throws SQLException, ClassNotFoundException {
        String update="UPDATE client SET firstName=?,lastName=?,address=? WHERE clientID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setInt(4,clientID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }



    //show client info
    public Client showInfo(int clientID) throws SQLException, ClassNotFoundException {
        String select="select * from client inner join account on client.clientID=account.clientID where client.clientid=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(select);

        preparedStatement.setInt(1,clientID);

        ResultSet resultSet=preparedStatement.executeQuery();

        Client client=new Client();
        Account account;
        List<Account> accountList=new ArrayList<>();

        while(resultSet.next()) {

            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            int accountID=resultSet.getInt(5);
            long balance=resultSet.getLong(6);

            client.setId(id);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setAddress(address);
            account=new Account(accountID,balance);
            accountList.add(account);
        }
        client.setAccount(accountList);
        preparedStatement.close();
        return client;
    }



    //
}
