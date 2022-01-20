package repository;

import connection.ConnectionProvider;
import model.Branch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRepo {

    public BranchRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }



    //add a branch
    public boolean add(String name,String address) throws SQLException, ClassNotFoundException {

        String insert="INSERT INTO branch (name,address) values(?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove a branch
    public boolean remove(int branchID) throws SQLException, ClassNotFoundException {

        String remove="DELETE FROM branch WHERE branchid=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,branchID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //update info of a branch
    public boolean update(int branchID,String name,String address) throws SQLException, ClassNotFoundException {
        String update="UPDATE branch SET name=?,address=? WHERE branchid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);
        preparedStatement.setInt(3,branchID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }


    //show branch info
    public Branch showInfo (int branchID) throws SQLException, ClassNotFoundException {
        String showInfo="SELECT * FROM branch where branchid=(?)";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setInt(1,branchID);
        ResultSet resultSet=preparedStatement.executeQuery();

        Branch branch=new Branch();

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            String address=resultSet.getString(3);

            branch.setId(id);
            branch.setName(name);
            branch.setAddress(address);

        }

        return branch;
    }



}
