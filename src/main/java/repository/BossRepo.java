package repository;

import connection.ConnectionProvider;
import model.BranchBoss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BossRepo {

    public BossRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }


    //CRUD



    //add a boss
    public boolean add(String firstName, String lastName,String address,int branchID) throws SQLException, ClassNotFoundException {

        String insert="insert into boss(firstName, lastName, address, branchID) values (?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setInt(4,branchID);


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
    public boolean update(int bossID,String firstName,String lastName,String address,int branchID) throws SQLException, ClassNotFoundException {
        String update="UPDATE boss SET firstname=?,lastname=?,address=?,branchid=? WHERE bossid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setInt(4,branchID);
        preparedStatement.setInt(5,bossID);

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
            String address=resultSet.getString(4);
            int branchID=resultSet.getInt(5);

            branchBoss.setId(id);
            branchBoss.setFirstName(firstName);
            branchBoss.setLastName(lastName);
            branchBoss.setAddress(address);
        }

        return branchBoss;
    }
}
