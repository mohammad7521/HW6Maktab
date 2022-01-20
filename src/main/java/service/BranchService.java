package service;

import model.Branch;
import repository.BranchRepo;

import java.sql.SQLException;

public class BranchService {

    private static BranchRepo branchRepo;

    static {
        try {
            branchRepo = new BranchRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add new branch
    public static boolean addNew(String name,String address) throws SQLException, ClassNotFoundException {

        return branchRepo.add(name,address);
    }


    //remove a branch
    public static boolean remove(int branchID) throws SQLException, ClassNotFoundException {

        Branch branch=branchRepo.showInfo(branchID);

        if(branch==null){
            return false;
        }
        else return branchRepo.remove(branchID);
    }



    //modify a branch
    public static boolean modify(int branchID,String name,String address) throws SQLException, ClassNotFoundException {

        Branch branch=branchRepo.showInfo(branchID);

        if(branch==null){
            return false;
        }

        return branchRepo.update(branchID,name,address);
    }
}
