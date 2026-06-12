package utils;

/**
 *
 * @author The Miracle Invoker
 */
public class ViewPlayer {

    public void printPlayerHeader() {
        UI.printDivider();
        System.out.printf("  %-8s | %-8s | %-25s | %-12s | %s%n",
                "PlayerID", "ClubID", "Player Name", "Position", "Shirt#");
        UI.printDivider();
    }
}
