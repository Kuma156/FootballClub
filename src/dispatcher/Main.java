package dispatcher;

import bussiness.Players;
import bussiness.Clubs;
import utils.Inputter;
import utils.Menu;
import utils.UI;
import java.io.IOException;
import model.Player;

/**
 * E:\Huy\,FPT\.Major\Summer26\Lab211\Lab\Lab1\TuLamGiaHuy_Lab1\MountainHiking
 * https://github.com/Kuma156/FootballClub
 *
 * @author The Miracle Invoker
 */
public class Main {

    public static void main(String[] args) {
        Clubs clubService = new Clubs();
        Players playerService = new Players();
        Files fileService = new Files(clubService, playerService);

        // Function 13 – Auto-load data on startup
        fileService.autoLoad();

        boolean running = true;
        while (running) {
            Menu.printMenu();
            int choice = Inputter.inputChoice(1, 14);

            switch (choice) {
                case 1:
                    clubService.listAllClubs();
                    break;
                case 2:
                    clubService.addClub();
                    break;
                case 3:
                    clubService.searchClubById();
                    break;
                case 4:
                    clubService.updateClub();
                    break;
                case 5:
                    clubService.listClubsByBudget();
                    break;
                case 6:
                    playerService.listPlayersSorted(clubService);
                    break;
                case 7:
                    playerService.searchByPartialName();
                    break;
                case 8:
                    playerService.addPlayer(clubService);
                    break;
                case 9:
                    playerService.removePlayer();
                    break;

                case 10:
                    playerService.updatePlayer();
                    break;
                case 11:
                    playerService.listByPosition();
                    break;
                case 12:
                    fileService.saveData();
                    break;
                case 13:
                    fileService.loadData();
                    break;
                case 14:
                    fileService.saveIfModified();

                    System.out.println("  Goodbye! Thank you for using EEL Management System.");
                    running = false;
                    break;

            }
            if (running) {
                System.out.println();
                System.out.print("  Press ENTER to continue...");
                Inputter.getScanner().nextLine();
            }
        }
    }

}
