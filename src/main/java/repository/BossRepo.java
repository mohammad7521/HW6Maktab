package repository;

import connection.ConnectionProvider;
import model.BranchBoss;
import model.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BossRepo {

    public BossRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }



    //CRUD



    //add a boss
    public boolean add(String firstName, String lastName, char gender, Date birthDate,int branchID) throws SQLException {

        String insert="INSERT INTO boss (default,?,?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,String.valueOf(gender));
        preparedStatement.setDate(4,birthDate);
        preparedStatement.setInt(5,branchID);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove a boss
    public boolean remove(int bossID) throws SQLException {

        String remove="DELETE FROM boss WHERE bossid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,bossID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //update info of a boss
    public boolean update(int bossID,String firstName,String lastName,char gender,Date birthDate) throws SQLException {
        String update="UPDATE boss SET firstname=?,lastname=?,gender,=?,birthdate=? WHERE bossid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,String.valueOf(gender));
        preparedStatement.setDate(5,birthDate);
        preparedStatement.setInt(5,bossID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }


    //show info of a boss
    public BranchBoss showInfo(int bossID) throws SQLException {
        String showInfo="SELECT * FROM boss where bossid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setInt(1,bossID);
        ResultSet resultSet=preparedStatement.executeQuery();

        BranchBoss branchBoss=null;

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String firstName=resultSet.getString(2);
            String lastName=resultSet.getString(3);
            String gender=resultSet.getString(4);
            Date birthDate=resultSet.getDate(5);
            int branchID=resultSet.getInt(6);

            branchBoss.setId(id);
            branchBoss.setFirstName(firstName);
            branchBoss.setLastName(lastName);
            branchBoss.setGender(gender.charAt(0));
            branchBoss.setBirthDay(birthDate);
        }

        return branchBoss;
    }
}
