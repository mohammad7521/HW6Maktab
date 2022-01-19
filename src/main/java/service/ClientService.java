package service;

import model.Client;
import repository.AccountRepo;
import repository.ClientRepo;

import java.sql.SQLException;

public class ClientService {

    private static ClientRepo clientRepo;




    //add new client
    public static boolean addNew (String firstName,String lastName,char gender,String address) throws SQLException {

        return clientRepo.add(firstName,lastName,address,gender);
    }


    //remove a client
    public static boolean remove(int clientID) throws SQLException {

        Client client=clientRepo.showInfo(clientID);
        if (client==null){
            return false;
        }
        else return clientRepo.remove(clientID);
    }



    //modify a client
    public static boolean modify(int clientID,String firstName,String lastName,String address,char gender) throws SQLException {

        Client client=clientRepo.showInfo(clientID);
        if (client==null){
            return false;
        }

        else return clientRepo.update(clientID,firstName,lastName,address,gender);
    }
}
