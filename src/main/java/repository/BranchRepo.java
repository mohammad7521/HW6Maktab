package repository;

import connection.ConnectionProvider;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BranchRepo {

    public BranchRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }



    //add a branch
    public boolean add(String name,String address) throws SQLException {

        String insert="INSERT INTO branch (default,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove a branch
    public boolean remove(int branchID) throws SQLException {

        String remove="DELETE FROM branch WHERE branchid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,branchID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //update info of a branch
    public boolean update(int branchID,String name,String address) throws SQLException {
        String update="UPDATE branch SET name=?,address=? WHERE branchid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);
        preparedStatement.setInt(3,branchID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }
}
