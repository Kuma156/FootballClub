package utils;

/**
 *
 * @author The Miracle Invoker
 */
public interface Acceptable {

    public final String DOUBLE_VALID = "^-?\\\\d*\\\\.?\\\\d+$";
    public final String INTEGER_VALID = "\\d+";
    public final String YESNO_VALID = "^(?i)(y|n|yes|no)$";

    public final String BUDGET_VALID = "^(0\\.\\d*[1-9]\\d*|[1-9]\\d*(\\.\\d+)?)$";
    //Part1: Must start with 0 and '.', late digit differs 0
    //Part2: Start with the digit differs 0

    public final String CLUB_VALID_ID = "^CL-\\d{4}$";
    //Format: CL-xxxx

    public final String NAME_VALID = "^[\\p{L}\\s]{2,20}$";
    public final String PLAYER_VALID_ID = "^P\\d{4}$";
    //Format: Pxxxx

    public final String POSITION_VALID = "(Goalkeeper|Defender|Midfielder|Forward|Winger)";
    public final String SHIRT_NUMBER_VALID = "^([1-9][0-9]?)$";
    //Must be between 1 and 99

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }

}
