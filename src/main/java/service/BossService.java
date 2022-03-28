package service;

import model.Branch;
import model.BranchBoss;
import org.postgresql.util.PSQLException;
import repository.BossRepo;

import java.sql.Date;
import java.sql.SQLException;

public class BossService {

    private static BossRepo bossRepo;

    static {
        bossRepo = new BossRepo();
    }


    //add new boss
    public static void addNew(String firstName, String lastName, String address, int branchID) {


        Branch branch = BranchService.showInfo(branchID);

        try {
            if (branchID != branch.getId()) {
                System.out.println("branch id does not exist! ");
            } else {
                bossRepo.add(firstName, lastName, address, branchID);
                System.out.println("boss added successfully");
            }
        } catch (PSQLException e) {
            System.out.println("this branch already has a boss! ");
        }
    }


    //remove a boss
    public static boolean remove(int bossID) {

        BranchBoss branchBoss;
        branchBoss = bossRepo.showInfo(bossID);

        if (branchBoss == null) {
            return false;
        }
        return bossRepo.remove(bossID);
    }


    //modify a boss
    public static void modify(int bossID, String firstName, String lastName, String address) {

        BranchBoss branchBoss = bossRepo.showInfo(bossID);

        if (branchBoss.getId() != bossID) {
            System.out.println("branch boss id does not exist");
        } else {
            bossRepo.update(bossID, firstName, lastName, address);
            System.out.println("branch boss has been modified successfully");
        }

    }


    //show info of a boss
    public static BranchBoss showInfo(int bossID) {

        return bossRepo.showInfo(bossID);
    }
}
