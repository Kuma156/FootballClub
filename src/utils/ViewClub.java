package utils;

/**
 *
 * @author The Miracle Invoker
 */
public class ViewClub {

    public void printClubHeader() {
        UI.printDivider();
        System.out.printf("  %-10s | %-30s | %-10s | %s%n",
                "Club ID", "Club Name", "Sponsor", "Budget (M EUR)");
        UI.printDivider();
    }

}
