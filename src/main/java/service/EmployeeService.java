package service;

import model.Branch;
import model.BranchBoss;
import model.Employee;
import repository.EmployeeRepo;

import java.sql.Date;
import java.sql.SQLException;

public class EmployeeService {

    private static EmployeeRepo employeeRepo;

    static {
        employeeRepo = new EmployeeRepo();
    }


    //add new employee
    public static boolean addNew(String firstName, String lastName,String address,int bossID)  {

        BranchBoss boss=BossService.showInfo(bossID);
        if (boss.getId()!=bossID){
            return  false;
        }
        else {
            return employeeRepo.add(firstName, lastName, address, bossID);
        }
    }


    //remove an employee
    public static boolean remove(int employeeID)  {

        Employee employee=employeeRepo.showInfo(employeeID);

        if(employee==null){
            return false;
        }

        return employeeRepo.remove(employeeID);
    }



    //modify an employee
    public static boolean modify(String firstName, String lastName,String address,int employeeID)  {

        Employee employee=employeeRepo.showInfo(employeeID);

        if(employee==null){
            return false;
        }

        return employeeRepo.update(employeeID,firstName,lastName,address);
    }



    //show info of an employee
    public static Employee showInfo(int employeeID)  {

        Employee employee=employeeRepo.showInfo(employeeID);
        return employee;
    }
}
