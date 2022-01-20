package service;

import model.Employee;
import repository.EmployeeRepo;

import java.sql.SQLException;

public class EmployeeService {

    private static EmployeeRepo employeeRepo;

    static {
        try {
            employeeRepo = new EmployeeRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add new employee
    public static boolean addNew(String firstName, String lastName,String address,int bossID) throws SQLException, ClassNotFoundException {

        return employeeRepo.add(firstName,lastName,address,bossID);
    }


    //remove an employee
    public static boolean remove(int employeeID) throws SQLException, ClassNotFoundException {

        Employee employee=employeeRepo.showInfo(employeeID);

        if(employee==null){
            return false;
        }

        return employeeRepo.remove(employeeID);
    }



    //modify an employee
    public static boolean modify(String firstName, String lastName,String address,int employeeID) throws SQLException, ClassNotFoundException {

        Employee employee=employeeRepo.showInfo(employeeID);

        if(employee==null){
            return false;
        }

        return employeeRepo.update(employeeID,firstName,lastName,address);
    }



    //show info of an employee
    public static Employee showInfo(int employeeID) throws SQLException, ClassNotFoundException {

        Employee employee=employeeRepo.showInfo(employeeID);
        return employee;
    }
}
