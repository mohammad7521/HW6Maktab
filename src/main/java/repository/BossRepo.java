package repository;

import connection.ConnectionProvider;
import model.BranchBoss;
import model.Employee;
import org.postgresql.util.PSQLException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BossRepo {

    public BossRepo() {
        ConnectionProvider.setConnection();
    }


    //CRUD



    //add a boss
    public boolean add(String firstName, String lastName,String address,int branchID) throws PSQLException {

        String insert="insert into boss(firstName, lastName, address, branchID) values (?,?,?,?)";

        int insertCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,address);
            preparedStatement.setInt(4,branchID);


            insertCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertCheck>0;
    }



    //remove a boss
    public boolean remove(int bossID) {

        String remove="DELETE FROM boss WHERE bossid=(?)";

        int removeCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(remove);
            preparedStatement.setInt(1,bossID);

            removeCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removeCheck >0;
    }



    //update info of a boss
    public boolean update(int bossID,String firstName,String lastName,String address) {
        String update="UPDATE boss SET firstname=?,lastname=?,address=? WHERE bossid=?";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,address);
            preparedStatement.setInt(4,bossID);

            updateCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return updateCheck >0;
    }


    //show info of a boss
    public BranchBoss showInfo(int bossID) {
        String showInfo="SELECT * FROM boss where bossid=?";

        BranchBoss branchBoss=new BranchBoss();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,bossID);
            ResultSet resultSet=preparedStatement.executeQuery();



            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String address = resultSet.getString(4);
                int branchID = resultSet.getInt(5);

                branchBoss.setId(id);
                branchBoss.setFirstName(firstName);
                branchBoss.setLastName(lastName);
                branchBoss.setAddress(address);
                branchBoss.setBranchID(branchID);

            }
            } catch(SQLException e){
                e.printStackTrace();
            }

        return branchBoss;
        }

}
