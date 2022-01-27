package service;

import model.Branch;
import repository.BranchRepo;

import java.sql.SQLException;

public class BranchService {

    private static BranchRepo branchRepo;

    static {
        branchRepo = new BranchRepo();

    }


    //add new branch
    public static boolean addNew(String name,String address) {

        return branchRepo.add(name,address);
    }


    //remove a branch
    public static boolean remove(int branchID)  {

        Branch branch=branchRepo.showInfo(branchID);

        if(branch==null){
            return false;
        }
        else return branchRepo.remove(branchID);
    }



    //modify a branch
    public static boolean modify(int branchID,String name,String address)  {

        Branch branch=branchRepo.showInfo(branchID);

        if(branch==null){
            return false;
        }

        return branchRepo.update(branchID,name,address);
    }



    //show info of a branch
    public static Branch showInfo(int branchID)  {
        return branchRepo.showInfo(branchID);
    }
}
