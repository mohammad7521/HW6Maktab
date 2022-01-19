package service;

import model.Client;
import repository.AccountRepo;
import repository.ClientRepo;

import java.sql.SQLException;
import java.text.ParseException;

public class ClientService {

    private static ClientRepo clientRepo;



    //add new client
    public static void addNew (long nationalCode,String firstName,String lastName,char gender,String address,int initialDeposit,int branchID) throws SQLException, ParseException {

        Client newClient=clientRepo.add(firstName,lastName,address,gender,nationalCode);
        AccountService.addNew(newClient.getId(),branchID,initialDeposit);

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


    //show client info
    public Client showInfo(int clientID) throws SQLException {
        Client client=null;
        client=clientRepo.showInfo(clientID);

        return client;
    }
}
