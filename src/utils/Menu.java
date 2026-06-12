package utils;

/**
 *
 * @author The Miracle Invoker
 */
public class Menu {

    public static void printUpdateMenu() {
        System.out.println("======================================");
        System.out.println("| 1. Update Name                     |");
        System.out.println("| 2. Update Phone Number             |");
        System.out.println("| 3. Update Email                    |");
        System.out.println("| 4. Update Mountain Peak Code       |");
        System.out.println("| 0. Return                          |");
        System.out.println("======================================");
        System.out.print("==> Your choice: ");
    }

    public static void printMainMenu() {
        System.out.println("=====Football Club & Player Management Menu=====");
        System.out.println("|1. List of all clubs                                |");
        System.out.println("|2. Add a new club                 |");
        System.out.println("|3. Search for a club by ID                         |");
        System.out.println("|4. Update a club by ID                 |");
        System.out.println("|5. List of all clubs with budget  ≤ input value                     |");
        System.out.println("|6. List all players in ascending order of club names, if same club, sort by shirt number ascending.                           |");
        System.out.println("|7. Search players by partial player name   |");
        System.out.println("|8. Add new player                               |");
        System.out.println("|9. Remove a player with ID                                |");
        System.out.println("|10. Update a player with an ID                                |");
        System.out.println("|11. List all players by a specific position                                |");
        System.out.println("|12. Save data to files                                |");
        System.out.println("|13. Load data from files                                |");
        System.out.println("|14. Quit program                                |");

        System.out.println("=====================================================");
        System.out.print("Please choose an option (1-9): ");
    }
}
