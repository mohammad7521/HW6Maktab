package repository;

import connection.ConnectionProvider;
import model.CreditCard;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class CreditCardRepo {

    public CreditCardRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }



    //CRUD



    //generate a credit card
    public boolean generate (long ccNumber, int cvv2,Date expireDate,int password,int accountID) throws SQLException, ClassNotFoundException {

            boolean checkInsert=false;
            String generate = "INSERT INTO creditcard (ccnumber,cvv2,expiredate,password,accountid,wrongpasswordentries) VALUES(?,?,?,?,?,0)";

            PreparedStatement preparedStatement = ConnectionProvider.setConnection().prepareStatement(generate);

            preparedStatement.setLong(1, ccNumber);
            preparedStatement.setInt(2, cvv2);
            preparedStatement.setDate(3, expireDate);
            preparedStatement.setInt(4, password);
            preparedStatement.setInt(5, accountID);

            if(preparedStatement.executeUpdate()>0){
                checkInsert=true;
            }

            preparedStatement.close();


        return checkInsert;
    }



    //remove a credit card
    public boolean remove(long ccNumber) throws SQLException, ClassNotFoundException {

        String remove="DELETE FROM creditcard WHERE ccnumber=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setLong(1,ccNumber);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //change credit card password
    public boolean changePassword (int password,long ccNumber) throws SQLException, ClassNotFoundException {
        String update="UPDATE creditcard SET password=? WHERE ccnumber=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setInt(1,password);
        preparedStatement.setLong(2,ccNumber);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }



    //show credit card info
    public CreditCard showInfo(long ccNumber) throws SQLException, ParseException, ClassNotFoundException {
        String select="select * from creditcard  where ccnumber=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(select);

        preparedStatement.setLong(1,ccNumber);

        ResultSet resultSet=preparedStatement.executeQuery();

        CreditCard creditCard=new CreditCard();
//        Account account;
//        List<Account> accountList=new ArrayList<>();

        while(resultSet.next()) {

            long numberOfCC = resultSet.getLong(1);
            int CVV2 = resultSet.getInt(2);
            Date expireDate = resultSet.getDate(3);
            int pass = resultSet.getInt(4);
            int accountID=resultSet.getInt(5);
            int wrongPassEntries=resultSet.getInt(6);

            creditCard.setCcNumber(numberOfCC);
            creditCard.setCVV2(CVV2);
            creditCard.setExpireDate(expireDate);
            creditCard.setPasscode(pass);
            creditCard.setWrongPasswordEntries(wrongPassEntries);

        }
        preparedStatement.close();
        return creditCard;

    }



    //wrong password entry
    public int wrongPasswordEntry(long ccNumber) throws SQLException, ClassNotFoundException {
        String wrongEntry="update creditCard set wrongPasswordEntries=wrongPasswordEntries+1 where ccNumber=? returning wrongpasswordentries";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(wrongEntry);
        preparedStatement.setLong(1,ccNumber);
        ResultSet resultSet=preparedStatement.executeQuery();

        resultSet.next();

        int numberOfWrongEntries=resultSet.getInt(1);
        preparedStatement.close();

        return numberOfWrongEntries;
    }
}
