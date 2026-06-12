package utils;

import java.util.Scanner;

/**
 *
 * @author The Miracle Invoker
 */
public class UI {

    public static void pause() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter to continue!");
        sc.nextLine();

    }

    public static void Loading() {
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
            }
            System.out.print(".");

        }
        System.out.println("");
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public static void printDivider() {
        for (int i = 0; i < 100; i++) {
            System.out.println("  " + "-");
        }
    }
    
    public void exitProcess() {
        System.out.println("-----EXIT APPLICATION-----");

        
            System.out.print("Do you want to save the changes before exiting? (Y/N): ");

            boolean confirm = input.inputYesNo();

            if (confirm == true) {

                studentList.saveData();
                System.out.println("Data saved successfully!");
            } else {
                System.out.println("You have unsaved changes. Are you sure you want to exit without saving? (Y/N)");
                confirm = input.inputYesNo();
                if (confirm == true) {
                    studentList.saveData();
                    System.out.println("Data saved successfully!");
                } else {
                    System.out.println("Exiting without saving changes...");
                }
            }
        }
        System.exit(0);
    }

}
