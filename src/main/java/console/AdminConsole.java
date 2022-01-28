package console;

import model.Branch;
import model.Client;
import model.Employee;
import service.BossService;
import service.BranchService;
import service.ClientService;
import service.EmployeeService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminConsole {

    public static void adminMenu()  {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Branch management");
            System.out.println("2-Branch boss management");
            System.out.println("3-Employee management");
            System.out.println("4-client management");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
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
                        flag = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number ! ");
            }
        }
    }



    public static void branchManagementMenu() {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add a branch");
            System.out.println("2-remove a branch");
            System.out.println("3-modify a branch");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
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
                                break;
                        }
                        break;

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
                        flag = false;
                        break;

                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number !");
            }
        }
    }







    public static void branchBossManagementMenu()   {

        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add a branch Boss");
            System.out.println("2-remove a branch Boss");
            System.out.println("3-modify a branch Boss");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter first name:");
                        String firstName = scanner.next();
                        System.out.println("enter last name:");
                        String lastName = scanner.next();
                        System.out.println("enter address:");
                        String address = scanner.next();
                        System.out.println("enter working branch ID:");
                        int branchId = scanner.nextInt();
                        scanner.nextLine();
                        BossService.addNew(firstName, lastName, address, branchId);
                        break;

                    case 2:
                        System.out.println("enter the desired branch boss ID");
                        int bossID = scanner.nextInt();
                        if (BossService.remove(bossID)) {
                            System.out.println("branch Boss removed successfully! ");
                            break;
                        } else System.out.println("branch boss ID does not exist! ");
                        break;
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
                        BossService.modify(ID, newName, newLastName, newAddress);

                        break;

                    case 0:
                        flag = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number ! ");
            }
        }
    }








    public static void employeeManagementMenu()  {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add an employee");
            System.out.println("2-remove an employee");
            System.out.println("3-modify an employee");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter first name:");
                        String firstName = scanner.next();
                        System.out.println("enter last name:");
                        String lastName = scanner.next();
                        System.out.println("enter address:");
                        String address = scanner.next();
                        System.out.println("enter their boss ID:");
                            try {
                                int bossID = scanner.nextInt();
                                scanner.nextLine();
                                if (EmployeeService.addNew(firstName, lastName, address, bossID)) {
                                    System.out.println("employee has been added successfully! ");
                                    break;
                                }else System.out.println("boss id does not exist ! ");
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("please enter a valid boss id ! ");
                                break;
                            }
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
                        Employee employee = EmployeeService.showInfo(ID);
                        if (employee.getId() != ID) {
                            System.out.println("employee id does not exist!");
                            break;
                        }
                        scanner.nextLine();
                        System.out.println("enter new first name:");
                        String newName = scanner.nextLine();
                        System.out.println("enter new last name:");
                        String newLastName = scanner.next();
                        System.out.println("enter new address:");
                        String newAddress = scanner.next();

                        if (EmployeeService.modify(newName, newLastName, newAddress, ID)) {
                            System.out.println("employee has been modified successfully! ");
                            break;
                        } else System.out.println("modification unsuccessful");
                        break;
                    case 0:
                        flag = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number ! ");
            }
        }
    }




    public static void clientManagement() {

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
                        System.out.println("enter their initial deposit:");
                        long initialDeposit=scanner.nextLong();
                        System.out.println("enter the branch ID:");
                        int branchID=scanner.nextInt();
                        scanner.nextLine();
                        ClientService.addNew(firstName,lastName,address,initialDeposit,branchID);
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
                        System.out.println("enter the desired client ID");
                        int ID = scanner.nextInt();
                        Client client=ClientService.showInfo(ID);
                        if (client.getId()!=ID){
                            System.out.println("client ID does not exist!");
                            break;
                        }
                        scanner.nextLine();
                        System.out.println("enter new first name:");
                        String newName = scanner.nextLine();
                        System.out.println("enter new last name:");
                        String newLastName = scanner.next();
                        System.out.println("enter new address:");
                        String newAddress=scanner.next();

                        if (ClientService.modify(ID,newName,newLastName,newAddress)) {
                            System.out.println("employee has been modified successfully! ");
                            break;
                        } else System.out.println("client ID does not exist! ");
                        break;
                    case 0:
                        flag=false;
                        break;
                }
            }else System.out.println("please enter a valid number! ");
        }
    }
}
