package repository;

import connection.ConnectionProvider;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreditCarRepo {

    public CreditCarRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }



    //CRUD



    //generate a credit card
    public boolean generate (long ccNumber, int cvv2,Date expireDate,int password,int accountID) throws SQLException {

        String generate="INSERT INTO creditcard (?,?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(generate);

        preparedStatement.setLong(1,ccNumber);
        preparedStatement.setInt(2,cvv2);
        preparedStatement.setDate(3,expireDate);
        preparedStatement.setInt(4,password);
        preparedStatement.setInt(5,accountID);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove a credit card
    public boolean remove(int ccNumber) throws SQLException {

        String remove="DELETE FROM creditcard WHERE ccnumber=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,ccNumber);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }
}
