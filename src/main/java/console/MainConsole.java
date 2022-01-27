package console;

import service.ClientService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainConsole {

    public static void mainMenu() throws SQLException, ClassNotFoundException, ParseException {

        while (true) {
            System.out.println("1-Admin: ");
            System.out.println("2-Client: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                switch (userSelect) {
                    case 1:
                        AdminConsole.adminMenu();
                        break;
                    case 2:
                        ClientConsole.clientMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            }
        }
    }
}
