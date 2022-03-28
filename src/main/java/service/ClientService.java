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

        clientRepo = new ClientRepo();
    }



    //add new client
    public static void addNew (String firstName,String lastName,String address,long initialDeposit,int branchID)  {
        Client newClient;

        newClient=clientRepo.add(firstName,lastName,address);
        if(createAccount(newClient.getId(),branchID,initialDeposit)) {
            System.out.println("client has been created successfully ! ");
        }

    }


    //remove a client
    public static boolean remove(int clientID)  {

        Client client=clientRepo.showInfo(clientID);
        if (client==null){
            return false;
        }
        else return clientRepo.remove(clientID);
    }



    //modify a client
    public static boolean modify(int clientID,String firstName,String lastName,String address) {
        Client client=clientRepo.showInfo(clientID);
        if (client==null){
            return false;
        }
        else return clientRepo.update(clientID,firstName,lastName,address);
    }



    //show client info
    public static Client showInfo(int clientID) {
        Client client;
        client=clientRepo.showInfo(clientID);

        return client;
    }



    //show client accounts
    public static void showAccountList(int clientID) {
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
    public static void createTransaction(int accountID, long destinationCc, long amount, String description, int cvv2,
                                  int password) {

        Account account=AccountService.showInfo(accountID);
        long ccNumber=account.getCreditCardNumber();

            CreditCardService.cardToCard(ccNumber, destinationCc, amount, description, password, cvv2);
    }


    //show transaction List
    public static void showTransactionList(int accountID) {
        List<Transaction> transactionList=TransactionService.showTransactionList(accountID);

        for (int i=0;i<transactionList.size();i++){
            System.out.println("-----transaction number "+i+1);
            System.out.println();
            System.out.println(transactionList.get(i).toString());
        }
    }


    //show transaction list based on a date
    public static void showTransactionList(int accountID,Date startDate) {
        List<Transaction> transactionList=TransactionService.showTransactionList(accountID,startDate);

        for (int i=0;i<transactionList.size();i++){
            System.out.println("-----transaction number "+ i);
            System.out.println(transactionList.get(i).toString());

        }
    }



    //change credit card password
    public static void changeCCPassword(int accountID,int oldPassword,int newPassword) {

        Account account=AccountService.showInfo(accountID);
        long ccNumber=account.getCreditCardNumber();

        if (CreditCardService.changeCCPassword(newPassword,oldPassword,ccNumber)){
            System.out.println("password changed successfully");
        }else System.out.println("old password is wrong!");
    }


    //create new account
    public static boolean createAccount(int clientID,int branchID,long initialDeposit) {

        boolean flag=false;
        if (AccountService.addNew(clientID, branchID, initialDeposit)) {
            flag=true;
            System.out.println("account created successfully! ");

        } else System.out.println("account creation failed \n branch id does not exist! ");
        return flag;
    }


    //deposit money to a specific account
    public static void deposit(int accountID,long amount)  {

        if (AccountService.addition(amount,accountID)){
            System.out.println("deposit successful");
        }
    }


    //withdraw money from a specific account
    public static void withDraw(int accountID,long amount,int password){

        Account account=AccountService.showInfo(accountID);
        CreditCard cc=CreditCardService.showInfo(account.getCreditCardNumber());
        long ccNumber=cc.getCcNumber();

        if (CreditCardService.passwordCheck(accountID,password)) {
            if (AccountService.deduction(amount, accountID)) {
                System.out.println("withdraw successful");
            } else System.out.println("not enough balance");
        }
        else {
            System.out.println("wrong password! ");
            CreditCardService.wrongPasswordEntry(ccNumber);
        }
    }
}
