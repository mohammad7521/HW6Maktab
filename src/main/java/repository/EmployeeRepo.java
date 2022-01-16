package repository;

import connection.ConnectionProvider;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRepo {
    public EmployeeRepo() throws SQLException {
        ConnectionProvider.setConnection();
    }

    //add an employee
    public boolean add(String firstName, String lastName, char gender,String address, int branchID,int bossID) throws SQLException {

        String insert="INSERT INTO employee (default,?,?,?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,String.valueOf(gender));
        preparedStatement.setString(4,address);
        preparedStatement.setInt(5,branchID);
        preparedStatement.setInt(6,bossID);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove an employee
    public boolean remove(int employeeID) throws SQLException {

        String remove="DELETE FROM employee WHERE employeeID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,employeeID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //update info of an employee
    public boolean update(int employeeID,String firstName, String lastName, char gender,String address, int branchID,int bossID) throws SQLException {
        String update="UPDATE employee SET firstname=?,lastname=?,gender=?,address=?,branchid=?,bossid=? WHERE employeeid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,String.valueOf(gender));
        preparedStatement.setString(5,address);
        preparedStatement.setInt(5,branchID);
        preparedStatement.setInt(6,bossID);
        preparedStatement.setInt(7,employeeID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }
}
