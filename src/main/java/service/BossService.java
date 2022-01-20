package service;

import model.Branch;
import model.BranchBoss;
import repository.BossRepo;

import java.sql.Date;
import java.sql.SQLException;

public class BossService {

    private static BossRepo bossRepo;

    static {
        try {
            bossRepo = new BossRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add new boss
    public static boolean addNew(String firstName, String lastName, char gender,String address, int branchID) throws SQLException, ClassNotFoundException {

        return bossRepo.add(firstName,lastName,gender,address,branchID);
    }


    //remove a boss
    public static boolean remove(int bossID) throws SQLException, ClassNotFoundException {

        BranchBoss branchBoss;
        branchBoss=bossRepo.showInfo(bossID);

        if(branchBoss==null){
            return false;
        }
        return bossRepo.remove(bossID);
    }



    //modify a boss
    public static boolean modify(int bossID,String firstName, String lastName, String address) throws SQLException, ClassNotFoundException {

        BranchBoss branchBoss=bossRepo.showInfo(bossID);

        if(branchBoss==null){
            return false;
        }
        return bossRepo.update(bossID,firstName,lastName,address);

    }
}
