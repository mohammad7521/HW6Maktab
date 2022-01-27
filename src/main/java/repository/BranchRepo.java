package repository;

import connection.ConnectionProvider;
import model.Branch;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRepo {

    public BranchRepo() {
        try {
            ConnectionProvider.setConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    //add a branch
    public boolean add(String name,String address) {

        String insert="INSERT INTO branch (name,address) values(?,?)";

        int insertCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);


            insertCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return insertCheck>0;
    }



    //remove a branch
    public boolean remove(int branchID) {

        String remove="DELETE FROM branch WHERE branchid=(?)";

        int removeCheck=0;

        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(remove);
            preparedStatement.setInt(1,branchID);

            removeCheck=preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return removeCheck >0;
    }



    //update info of a branch
    public boolean update(int branchID,String name,String address)  {
        String update="UPDATE branch SET name=?,address=? WHERE branchid=?";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setInt(3,branchID);

            updateCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return updateCheck >0;
    }


    //show branch info
    public Branch showInfo (int branchID) {
        String showInfo="SELECT * FROM branch where branchid=(?)";

        Branch branch=new Branch();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,branchID);
            ResultSet resultSet=preparedStatement.executeQuery();



            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);

                branch.setId(id);
                branch.setName(name);
                branch.setAddress(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return branch;
    }



}
