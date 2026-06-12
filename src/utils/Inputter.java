package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author The Miracle Invoker
 */
public class Inputter implements Acceptable {

    private static Scanner scanner;

    public Inputter() {
        this.scanner = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.print(mess);
        return scanner.nextLine();
    }

    public int getInt(String mess) {
        int result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID)) {
            result = Integer.parseInt(temp);
        }
        return result;
    }

    public double getDouble(String mess) {
        double result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID)) {
            result = Double.parseDouble(temp);
        }
        return result;
    }

    public boolean inputYesNo() {
        String temp = "";
        boolean loopMore = true;

        do {

            temp = scanner.nextLine().trim().toLowerCase();
            if (temp.isEmpty()) {
                continue;
            }

            if (!Acceptable.isValid(temp, Acceptable.YESNO_VALID)) {
                System.out.println("Your choose is invalid!. Re-enter...");
            } else {
                loopMore = false;
            }
        } while (loopMore);

        return !("n".equals(temp) || "no".equals(temp));
    }

    public String inputAndLoop(String mess, String pattern) {
        String result = "";
        boolean again = true;
        boolean more = true;
        int count = 0;
        String temp;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more) {
                count++;
                System.out.println("Data is invalid! Re-enter...");
            }

            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = inputYesNo();

                if (!again) {
                    return "";
                } else {
                    count = 0;
                }
            }
        } while (more);
        return result.trim();
    }

    public String nameProcessor(String name) {
        name = name.trim();
        if (name.isEmpty()) {
            return "";
        }
        StringBuffer tmp = new StringBuffer();
        tmp.setLength(0);
        String[] parts = name.split("\\s+");
        for (String part : parts) {
            part = part.trim();
            part = part.toLowerCase();
            part = part.substring(0, 1).toUpperCase() + part.substring(1);
            tmp.append(part + " ");
        }

        name = tmp.toString().trim();
        return name;
    }

    public static int inputChoice(int min, int max) {

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < min || choice > max) {
                    System.out.println("Choice must be from " + min + " to " + max);
                    System.out.println("Please enter again!");
                    System.out.print("Input your choice: ");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter valid number: ");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    // Read a non-empty string from user 
    public String readNonEmpty(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input.trim();
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // Read a string, allow empty (means "skip")
    public String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    //Read a double from optional input (returns -1 if empty)
    public double inputDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return -1;
            }
            Acceptable.isValid(input, Acceptable.BUDGET_VALID);
        }
    }

    // Read budget money
    public double inputBudget(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (Validator.isValidBudget(input)) {
                return Double.parseDouble(input);
            } else {
                return -1;
            }
        }
    }

    //Read an integer in the range 
    public int inputShirtNumber(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
    
    public static Scanner getScanner() {
        return scanner;
    }
}
