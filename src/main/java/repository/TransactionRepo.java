package repository;

import connection.ConnectionProvider;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class TransactionRepo {

    public TransactionRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }



    //add a transaction
    public boolean add(Date date, Time time, long ccNumber, long destinationCCNumber, int amount,String description) throws SQLException {

        String insert="INSERT INTO transaction (default,?,?,?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setDate(1,date);
        preparedStatement.setTime(2,time);
        preparedStatement.setInt(3,amount);
        preparedStatement.setLong(4,ccNumber);
        preparedStatement.setLong(5,destinationCCNumber);
        preparedStatement.setString(6,description);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }

}
