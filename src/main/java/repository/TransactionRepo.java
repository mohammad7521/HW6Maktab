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
    public boolean add(Date date, Time time,String ccNumber,String destinationCCNumber) throws SQLException {

        String insert="INSERT INTO transaction (default,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setDate(1,date);
        preparedStatement.setTime(2,time);
        preparedStatement.setString(3,ccNumber);
        preparedStatement.setString(4,destinationCCNumber);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }

}
