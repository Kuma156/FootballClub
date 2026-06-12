package utils;

import java.util.Scanner;

/**
 *
 * @author The Miracle Invoker
 */
public class Validator implements Acceptable {

    // 1. Validate Club ID (Format: CL-xxxx)
    public static boolean isValidClubId(String clubId) {
        return Acceptable.isValid(clubId, CLUB_VALID_ID);
    }

    // 2. Validate Player ID (Format: Pxxxx)
    public static boolean isValidPlayerId(String playerId) {
        return Acceptable.isValid(playerId, PLAYER_VALID_ID);
    }

    // 3. Validate Name (Length 2-20, letters and spaces only)
    public static boolean isValidName(String name) {
        return Acceptable.isValid(name, NAME_VALID);
    }

    // 4. Validate Budget (Positive number greater than 0, decimals allowed)
    public static boolean isValidBudget(String budget) {
        return Acceptable.isValid(budget, BUDGET_VALID);
    }

    // 5. Validate Player Position (Must be one of the 5 allowed positions)
    public static boolean isValidPosition(String position) {
        return Acceptable.isValid(position, Acceptable.POSITION_VALID);
    }

    // 6. Validate Shirt Number (From 1 to 99)
    public static boolean isValidShirtNumber(String shirtNumber) {
        return Acceptable.isValid(shirtNumber, Acceptable.SHIRT_NUMBER_VALID);
    }

    // 7. Validate Yes/No choices (y, n, yes, no - case-insensitive)
    public static boolean isValidYesNo(String input) {
        return Acceptable.isValid(input, YESNO_VALID);
    }

    // 8. General Integer validation
    public static boolean isValidInteger(String input) {
        return Acceptable.isValid(input, INTEGER_VALID);
    }

    // 9. General Double validation
    public static boolean isValidDouble(String input) {
        return Acceptable.isValid(input, DOUBLE_VALID);
    }

}
