package repository;

import connection.ConnectionProvider;
import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepo {
    public EmployeeRepo() throws SQLException, ClassNotFoundException {
        ConnectionProvider.setConnection();
    }

    //add an employee
    public boolean add(String firstName, String lastName,String address,int bossID) throws SQLException, ClassNotFoundException {

        String insert="INSERT INTO EMPLOYEE(firstname,lastname,address,bossid) VALUES(?,?,?,?)";

        PreparedStatement preparedStatement= ConnectionProvider.setConnection().prepareStatement(insert);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setInt(4,bossID);


        int insertCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return insertCheck>0;
    }



    //remove an employee
    public boolean remove(int employeeID) throws SQLException, ClassNotFoundException {

        String remove="DELETE FROM employee WHERE employeeID=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(remove);

        preparedStatement.setInt(1,employeeID);

        int removeCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return removeCheck >0;
    }



    //update info of an employee
    public boolean update(int employeeID,String firstName, String lastName,String address) throws SQLException, ClassNotFoundException {
        String update="UPDATE employee SET firstname=?,lastname=?,address=? WHERE employeeid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(update);

        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,address);
        preparedStatement.setInt(4,employeeID);

        int updateCheck=preparedStatement.executeUpdate();

        preparedStatement.close();

        return updateCheck >0;
    }


    //show employee info
    public Employee showInfo(int employeeID) throws SQLException, ClassNotFoundException {
        String showInfo="SELECT * FROM employee where employeeid=?";

        PreparedStatement preparedStatement=ConnectionProvider.setConnection().prepareStatement(showInfo);
        preparedStatement.setInt(1,employeeID);
        ResultSet resultSet=preparedStatement.executeQuery();

        Employee employee=new Employee();

        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String firstName=resultSet.getString(2);
            String lastName=resultSet.getString(3);
            String address=resultSet.getString(4);


            employee.setId(id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setAddress(address);

        }

        return employee;
    }
}
