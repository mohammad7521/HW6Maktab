package repository;

import connection.ConnectionProvider;
import model.Account;
import model.Client;
import model.CreditCard;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardRepo {

    public CreditCardRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }



    //CRUD



    //generate a credit card
    public boolean generate (long ccNumber, int cvv2,Date expireDate,int password,int accountID) throws SQLException {


        //checking if the cc is and cvv2 is not duplicate
        String checkCC = "select * from creditCard where ccNumber=?";
        String checkCVV2 = "select * from creditCard where cvv2=?";

        PreparedStatement checkCCStatement = ConnectionProvider.setConnection().prepareStatement(checkCC);
        PreparedStatement checkCVV2Statement = ConnectionProvider.setConnection().prepareStatement(checkCVV2);

        boolean checkInsert=false;
        if (checkCCStatement.executeUpdate() < 1 && checkCVV2Statement.executeUpdate() < 1) {

            String generate = "INSERT INTO creditcard (?,?,?,?,?)";

            PreparedStatement preparedStatement = ConnectionProvider.setConnection().prepareStatement(generate);

            preparedStatement.setLong(1, ccNumber);
            preparedStatement.setInt(2, cvv2);
            preparedStatement.setDate(3, expireDate);
            preparedStatement.setInt(4, password);
            preparedStatement.setInt(5, accountID);
            preparedStatement.executeUpdate();

            if(preparedStatement.executeUpdate()>0){
                checkInsert=true;
            }

            preparedStatement.close();
        }

        return checkInsert;
    }



    //remove a credit card
    public boolean remove(long ccNumber) throws SQLException {

        String remove="DELETE FROM creditcard WHERE ccnumber=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setLong(1,ccNumber);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //change credit card password
    public boolean changePassword (int password,long ccNumber) throws SQLException {
        String update="UPDATE creditcard SET password=? WHERE ccnumber=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,password);
        preparedStatement.setLong(2,ccNumber);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }



    //show credit card info
    public CreditCard showInfo(long ccNumber) throws SQLException, ParseException {
        String select="select * from creditcard  where ccnumber=?;";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(select);

        preparedStatement.setLong(1,ccNumber);

        ResultSet resultSet=preparedStatement.executeQuery();

        CreditCard creditCard=null;
//        Account account;
//        List<Account> accountList=new ArrayList<>();

        while(resultSet.next()) {

            int numberOfCC = resultSet.getInt(1);
            int CVV2 = resultSet.getInt(2);
            Date expireDate = resultSet.getDate(3);
            int pass = resultSet.getInt(4);

            creditCard=new CreditCard();

            creditCard.setCcNumber(numberOfCC);
            creditCard.setCVV2(CVV2);
            creditCard.setExpireDate(expireDate);
            creditCard.setPasscode(pass);

        }
        preparedStatement.close();

        return creditCard;
    }
}
