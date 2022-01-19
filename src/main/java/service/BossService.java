package service;

import model.Branch;
import model.BranchBoss;
import repository.BossRepo;

import java.sql.Date;
import java.sql.SQLException;

public class BossService {

    private static BossRepo bossRepo;



    //add new boss
    public static boolean addNew(String firstName, String lastName, char gender, Date birthDate, int branchID) throws SQLException {

        return bossRepo.add(firstName,lastName,gender,birthDate,branchID);
    }


    //remove a boss
    public static boolean remove(int bossID) throws SQLException {

        BranchBoss branchBoss=bossRepo.showInfo(bossID);

        if(branchBoss==null){
            return false;
        }
        return bossRepo.remove(bossID);
    }



    //modify a boss
    public static boolean modify(int bossID,String firstName, String lastName, char gender, Date birthDate) throws SQLException {

        BranchBoss branchBoss=bossRepo.showInfo(bossID);

        if(branchBoss==null){
            return false;
        }
        return bossRepo.update(bossID,firstName,lastName,gender,birthDate);

    }
}
