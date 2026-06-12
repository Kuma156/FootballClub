package utils;

/**
 *
 * @author The Miracle Invoker
 */
public class Menu {

    public static void printMenu() {
        System.out.println();
        System.out.println("  ╔═════════════════════════════════════════════════════════╗");
        System.out.println("  ║       EUROPEAN ELITE LEAGUE – Management System         ║");
        System.out.println("  ╠═════════════════════════════════════════════════════════╣");
        System.out.println("  ║  CLUB MANAGEMENT                                        ║");
        System.out.println("  ║   1. List all clubs                                     ║");
        System.out.println("  ║   2. Add a new club                                     ║");
        System.out.println("  ║   3. Search for a club by ID                            ║");
        System.out.println("  ║   4. Update a club by ID                                ║");
        System.out.println("  ║   5. List clubs with budget ≤ input value               ║");
        System.out.println("  ╠═════════════════════════════════════════════════════════╣");
        System.out.println("  ║  PLAYER MANAGEMENT                                      ║");
        System.out.println("  ║   6. List all players (sorted by club, then shirt)      ║");
        System.out.println("  ║   7. Search players by partial name                     ║");
        System.out.println("  ║   8. Add a new player                                   ║");
        System.out.println("  ║   9. Remove a player by ID                              ║");
        System.out.println("  ║  10. Update a player by ID                              ║");
        System.out.println("  ║  11. List all players by position                       ║");
        System.out.println("  ╠═════════════════════════════════════════════════════════╣");
        System.out.println("  ║  DATA                                                   ║");
        System.out.println("  ║  12. Save data to files                                 ║");
        System.out.println("  ║  13. Load data from files                               ║");
        System.out.println("  ║  14. Quit program                                       ║");
        System.out.println("  ╚═════════════════════════════════════════════════════════╝");
    }
}
