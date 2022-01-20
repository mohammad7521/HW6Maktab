package console;

import model.Branch;
import service.BossService;
import service.BranchService;
import service.ClientService;
import service.EmployeeService;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminConsole {

    public static void adminMenu() throws SQLException, ClassNotFoundException, ParseException {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Branch management");
            System.out.println("2-Branch boss management");
            System.out.println("3-Employee management");
            System.out.println("4-client management");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        branchManagementMenu();
                        break;
                    case 2:
                        branchBossManagementMenu();
                        break;
                    case 3:
                        employeeManagementMenu();
                        break;
                    case 4:
                        clientManagement();
                    case 0:
                        flag=false;
                        break;
                }
            } else System.out.println("please enter a number! ");
        }
    }



    public static void branchManagementMenu() throws SQLException, ClassNotFoundException {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add a branch");
            System.out.println("2-remove a branch");
            System.out.println("3-modify a branch");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter new branch name:");
                        String branchName = scanner.next();
                        System.out.println("enter new branch address:");
                        String address = scanner.next();
                        if (BranchService.addNew(branchName, address)) {
                            System.out.println("branch has been created successfully! ");
                        }
                        break;

                    case 2:
                        while (true) {
                            System.out.println("enter the desired branch ID");
                            int branchID = scanner.nextInt();
                            if (BranchService.remove(branchID)) {
                                System.out.println("branch has been removed successfully! ");
                                break;
                            } else System.out.println("branch ID does not exist! ");
                        }

                    case 3:
                        while (true) {
                            System.out.println("enter the desired branch ID");
                            int branchID = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("enter new name:");
                            String newName = scanner.nextLine();
                            System.out.println("enter new address:");
                            String branchAddress = scanner.next();

                            if (BranchService.modify(branchID, newName, branchAddress)) {
                                System.out.println("branch has been modified successfully! ");
                            } else System.out.println("branch ID does not exist! ");
                            break;
                        }
                    case 0:
                        flag=false;
                        break;

                }
            }else System.out.println("please enter a number! ");
        }
    }







    public static void branchBossManagementMenu() throws SQLException, ClassNotFoundException {

        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add a branch Boss");
            System.out.println("2-remove a branch Boss");
            System.out.println("3-modify a branch Boss");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter first name:");
                        String firstName = scanner.next();
                        System.out.println("enter last name:");
                        String lastName = scanner.next();
                        System.out.println("enter gender:");
                        String gender = scanner.next();
                        System.out.println("enter address:");
                        String address = scanner.next();
                        System.out.println("enter working branch ID:");
                        int branchId = scanner.nextInt();
                        scanner.nextLine();
                        if (BossService.addNew(firstName, lastName, gender.charAt(0), address, branchId)) {
                            System.out.println("branch boss has been added successfully! ");
                        }
                        break;

                    case 2:
                        System.out.println("enter the desired branch boss ID");
                        int bossID = scanner.nextInt();
                        if (BossService.remove(bossID)) {
                            System.out.println("branch Boss removed successfully! ");
                            break;
                        } else System.out.println("branch boss ID does not exist! ");

                    case 3:

                        System.out.println("enter the desired branch boss ID");
                        int ID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("enter new first name:");
                        String newName = scanner.nextLine();
                        System.out.println("enter new last name:");
                        String newLastName = scanner.next();
                        System.out.println("enter new address:");
                        String newAddress = scanner.next();

                        if (BossService.modify(ID, newName, newLastName, newAddress)) {
                            System.out.println("branch boss has been modified successfully! ");
                        } else System.out.println("branch boss ID does not exist! ");
                        break;

                    case 0:
                        flag=false;
                        break;
                }
            }else System.out.println("please enter a number! ");
        }
    }








    public static void employeeManagementMenu() throws SQLException, ClassNotFoundException {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add an employee");
            System.out.println("2-remove an employee");
            System.out.println("3-modify an employee");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter first name:");
                        String firstName = scanner.next();
                        System.out.println("enter last name:");
                        String lastName = scanner.next();
                        System.out.println("enter gender:");
                        String gender=scanner.next();
                        System.out.println("enter address:");
                        String address=scanner.next();
                        System.out.println("enter working branch ID:");
                        int branchId=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("enter their boss ID:");
                        int bossID=scanner.nextInt();
                        scanner.nextLine();
                        if (EmployeeService.addNew(firstName,lastName,gender.charAt(0),address,branchId,bossID)) {
                            System.out.println("employee has been added successfully! ");
                        }
                        break;

                    case 2:
                            System.out.println("enter the desired employee ID");
                            int employeeID = scanner.nextInt();
                            if (EmployeeService.remove(employeeID)) {
                                System.out.println("employee removed successfully! ");
                                break;
                            } else System.out.println("employeeID does not exist! ");
                            break;


                    case 3:
                            System.out.println("enter the desired employee ID");
                            int ID = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("enter new first name:");
                            String newName = scanner.nextLine();
                            System.out.println("enter new last name:");
                            String newLastName = scanner.next();
                            System.out.println("enter new address:");
                            String newAddress=scanner.next();

                            if (EmployeeService.modify(newName,newLastName,newAddress,ID)) {
                                System.out.println("employee has been modified successfully! ");
                                break;
                            } else System.out.println("employee ID does not exist! ");
                            break;
                    case 0:
                        flag=false;
                        break;
                }
            }else System.out.println("please enter a number! ");
        }
    }




    public static void clientManagement() throws SQLException, ClassNotFoundException, ParseException {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add a client");
            System.out.println("2-remove a client");
            System.out.println("3-modify a client");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter first name:");
                        String firstName = scanner.next();
                        System.out.println("enter last name:");
                        String lastName = scanner.next();
                        System.out.println("enter address:");
                        String address=scanner.next();
                        System.out.println("enter gender:");
                        String gender=scanner.next();
                        System.out.println("enter their initial deposit");
                        int initialDeposit=scanner.nextInt();
                        System.out.println("enter the branch ID");
                        int branchID=scanner.nextInt();
                        scanner.nextLine();
                        if (ClientService.addNew(firstName,lastName,gender.charAt(0),address,initialDeposit,branchID)) {
                            System.out.println("client has been added successfully! ");
                        }
                        break;

                    case 2:
                        System.out.println("enter the desired client ID");
                        int clientID = scanner.nextInt();
                        if (ClientService.remove(clientID)) {
                            System.out.println("client was removed successfully! ");
                            break;
                        } else System.out.println("client does not exist! ");
                        break;


                    case 3:
                        System.out.println("enter the desired employee ID");
                        int ID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("enter new first name:");
                        String newName = scanner.nextLine();
                        System.out.println("enter new last name:");
                        String newLastName = scanner.next();
                        System.out.println("enter new address:");
                        String newAddress=scanner.next();

                        if (EmployeeService.modify(newName,newLastName,newAddress,ID)) {
                            System.out.println("employee has been modified successfully! ");
                            break;
                        } else System.out.println("employee ID does not exist! ");
                        break;
                    case 0:
                        flag=false;
                        break;
                }
            }else System.out.println("please enter a number! ");
        }
    }
}
