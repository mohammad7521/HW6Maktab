package service;

import model.Account;
import model.Client;
import model.CreditCard;
import model.Transaction;
import repository.AccountRepo;
import repository.ClientRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ClientService {

    private static ClientRepo clientRepo;

    static {
        try {
            clientRepo = new ClientRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //add new client
    public static boolean addNew (String firstName,String lastName,char gender,String address,int initialDeposit,int branchID) throws SQLException, ParseException, ClassNotFoundException {
        Client newClient;

        newClient=clientRepo.add(firstName,lastName,address,gender);
        return AccountService.addNew(newClient.getId(),branchID,initialDeposit);

    }


    //remove a client
    public static boolean remove(int clientID) throws SQLException, ClassNotFoundException {

        Client client=clientRepo.showInfo(clientID);
        if (client==null){
            return false;
        }
        else return clientRepo.remove(clientID);
    }



    //modify a client
    public static boolean modify(int clientID,String firstName,String lastName,String address,char gender) throws SQLException, ClassNotFoundException {

        Client client=clientRepo.showInfo(clientID);
        if (client==null){
            return false;
        }
        else return clientRepo.update(clientID,firstName,lastName,address,gender);
    }



    //show client info
    public static Client showInfo(int clientID) throws SQLException, ClassNotFoundException {
        Client client=null;
        client=clientRepo.showInfo(clientID);

        return client;
    }



    //show client accounts
    public static void showAccountList(int clientID) throws SQLException, ClassNotFoundException {
        Client client=showInfo(clientID);
        List<Account> accountList=client.getAccount();
        System.out.printf("%10s %20s","account ID","account balance");
        System.out.println();
        for (Account account:accountList){
            System.out.printf("%5s %18d",account.getId(),account.getBalance());
            System.out.println();
        }
    }



    //add new transaction
    public static void createTransaction(int accountID, long destinationCc, int amount, String description, int cvv2,
                                  int password) throws SQLException, ClassNotFoundException, ParseException {

        Account account=AccountService.showInfo(accountID);
        long ccNumber=account.getCreditCardNumber();

            CreditCardService.cardToCard(ccNumber, destinationCc, amount, description, password, cvv2);
    }


    //show transaction List
    public static void showTransactionList(int accountID) throws SQLException, ClassNotFoundException {
        List<Transaction> transactionList=TransactionService.showTransactionList(accountID);

        for (int i=0;i<transactionList.size();i++){
            System.out.println("-----transaction number "+i+1);
            System.out.println();
            System.out.println(transactionList.get(i).toString());
        }
    }


    //show transaction list based on a date
    public static void showTransactionList(int accountID,Date startDate) throws SQLException, ClassNotFoundException {
        List<Transaction> transactionList=TransactionService.showTransactionList(accountID);

        for (int i=0;i<transactionList.size();i++){
            System.out.println("-----transaction number "+i+1);
            System.out.println();
            System.out.println(transactionList.get(i).toString());
        }
    }

}
