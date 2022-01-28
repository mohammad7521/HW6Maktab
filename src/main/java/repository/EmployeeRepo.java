package repository;

import connection.ConnectionProvider;
import model.Branch;
import model.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepo {
    public EmployeeRepo() {
        try {
            ConnectionProvider.setConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //add an employee
    public boolean add(String firstName, String lastName,String address,int bossID) {

        String insert="INSERT INTO EMPLOYEE(firstname,lastname,address,bossid) VALUES(?,?,?,?)";

        int insertCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(insert);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,address);
            preparedStatement.setInt(4,bossID);

            insertCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return insertCheck>0;
    }



    //remove an employee
    public boolean remove(int employeeID) {

        String remove="DELETE FROM employee WHERE employeeID=?";

        int removeCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(remove);
            preparedStatement.setInt(1,employeeID);

            removeCheck=preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return removeCheck >0;
    }



    //update info of an employee
    public boolean update(int employeeID,String firstName, String lastName,String address)  {
        String update="UPDATE employee SET firstname=?,lastname=?,address=? WHERE employeeid=?";

        int updateCheck=0;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(update);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,address);
            preparedStatement.setInt(4,employeeID);

            updateCheck=preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return updateCheck >0;
    }


    //show employee info
    public Employee showInfo(int employeeID)  {

        String showInfo="SELECT * FROM employee where employeeid=?";

        Employee employee=new Employee();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = ConnectionProvider.setConnection().prepareStatement(showInfo);
            preparedStatement.setInt(1,employeeID);
            ResultSet resultSet=preparedStatement.executeQuery();



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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return employee;
    }
}
