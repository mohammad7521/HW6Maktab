package console;

import service.ClientService;
import service.CreditCardService;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class ClientConsole {

    public static void clientMenu() throws SQLException, ClassNotFoundException, ParseException {

        boolean flag=true;
        System.out.println("enter your ID");
        Scanner scanner = new Scanner(System.in);
        int id=scanner.nextInt();
        while (flag) {
            System.out.println();
            System.out.println("1-Show accounts list");
            System.out.println("2-create a transaction");
            System.out.println("3-show All transactions");
            System.out.println("4-show transaction based on specific date");
            System.out.println("5-Change password");
            System.out.println("0-exit");



            if (scanner.hasNextInt()) {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        ClientService.showAccountList(id);
                        break;
                    case 2:
                        System.out.println("select your account by ID");
                        ClientService.showAccountList(id);
                        int accountID=scanner.nextInt();
                        System.out.println("enter amount: ");
                        int amount=scanner.nextInt();
                        System.out.println("enter destination ccNumber");
                        long destinationCC=scanner.nextLong();
                        System.out.println("enter your cvv2");
                        int cvv2=scanner.nextInt();
                        System.out.println("enter your password");
                        int password=scanner.nextInt();
                        System.out.println("enter description");
                        scanner.nextLine();
                        String description=scanner.next();
                        ClientService.createTransaction(accountID,destinationCC,amount,description
                        ,cvv2,password);
                        break;
                    case 3:
                        ClientService.showAccountList(id);
                        System.out.println("select the account");
                        accountID=scanner.nextInt();
                        ClientService.showTransactionList(accountID);
                        break;
                    case 4:
                        ClientService.showAccountList(id);
                        System.out.println("enter starting date in the following format");
                        System.out.println("yyyy-mm-dd");
                        Date startDate= Date.valueOf(scanner.next());
                        System.out.println("enter the account id");
                        accountID= scanner.nextInt();
                        ClientService.showTransactionList(accountID,startDate);
                        break;
                    case  5:
                        ClientService.showAccountList(id);
                        System.out.println("enter the account id");
                        accountID=scanner.nextInt();
                        int oldPass=scanner.nextInt();
                        int newPass=scanner.nextInt();
                        ClientService.changeCCPassword(accountID,oldPass,newPass);
//                    case 0:
//                        flag=false;
//                        break;
                }
            } else System.out.println("please enter a number! ");
        }
    }
}
