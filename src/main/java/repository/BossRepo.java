package repository;

import connection.ConnectionProvider;
import model.BranchBoss;
import model.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BossRepo {

    public BossRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }


    //CRUD



    //add a boss
    public boolean add(String firstName, String lastName, char gender,String address,int branchID) throws SQLException, ClassNotFoundException {

        String insert="insert into boss(firstName, lastName, gender, address, branchID) values (?,?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,String.valueOf(gender));
        preparedStatement.setString(4,address);
        preparedStatement.setInt(5,branchID);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove a boss
    public boolean remove(int bossID) throws SQLException, ClassNotFoundException {

        String remove="DELETE FROM boss WHERE bossid=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,bossID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //update info of a boss
    public boolean update(int bossID,String firstName,String lastName,String address) throws SQLException, ClassNotFoundException {
        String update="UPDATE boss SET firstname=?,lastname=?,address=? WHERE bossid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setInt(4,bossID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }


    //show info of a boss
    public BranchBoss showInfo(int bossID) throws SQLException, ClassNotFoundException {
        String showInfo="SELECT * FROM boss where bossid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setInt(1,bossID);
        ResultSet resultSet=preparedStatement.executeQuery();

        BranchBoss branchBoss=new BranchBoss();

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String firstName=resultSet.getString(2);
            String lastName=resultSet.getString(3);
            String gender=resultSet.getString(4);
            String address=resultSet.getString(5);
            int branchID=resultSet.getInt(6);

            branchBoss.setId(id);
            branchBoss.setFirstName(firstName);
            branchBoss.setLastName(lastName);
            branchBoss.setGender(gender.charAt(0));
            branchBoss.setAddress(address);
        }

        return branchBoss;
    }
}
