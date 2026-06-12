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
 * https://github.com/Kuma156/MountainHiking
 *
 * @author The Miracle Invoker
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Clubs clubList = new Clubs();
        Players playerList = new Players();

        clubList.loadData();
        playerList.loadData();

        PlayerController controller = new PlayerController(playerList, clubList);

        UI.clearScreen();

        while (true) {

            Menu.printMainMenu();

            int choice = Inputter.inputChoice(1, 14);

            switch (choice) {
                case 1: //new register
                    controller.addStudentProcess();
                    break;
                case 2: //update info
                    controller.updateStudentProcess();
                    break;
                case 3: //show all student list
                    controller.displayAllStudentsProcess();
                    break;
                case 4: //delete student
                    controller.deleteStudentProcess();
                    break;
                case 5: //search participant by name
                    controller.searchStudentByNameProcess();
                    break;
                case 6: //filter by campus
                    controller.filterDataByCampusProcess();
                    break;
                case 7: //statistics
                    controller.showStatisticsProcess();
                    break;
                case 8: //save                    
                    System.out.println("This function is not available.");
                    break;
                case 14: //exit
                    controller.exitProcess();
                    break;
                default:
                    System.out.println("This function is not available.");
            }
            UI.pause();
        }
    }

}
