import java.util.Scanner;

public class SafeInput {

    /**
     * Gets a String that is not empty.
     *
     * @param pipe Scanner used to read input
     * @param prompt Message displayed to the user
     * @return A non-empty String
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";

        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }
    /**
     * Gets an integer with no range restrictions.
     *
     * @param pipe Scanner used to read input
     * @param prompt Message displayed to the user
     * @return A valid integer
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets a double value with no range restrictions.
     *
     * @param pipe Scanner used to read input
     * @param prompt Message displayed to the user
     * @return A valid double
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets an integer within an inclusive range.
     *
     * @param pipe Scanner used to read input
     * @param prompt Message displayed to the user
     * @param low Minimum accepted value
     * @param high Maximum accepted value
     * @return An integer within the specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Value must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an integer: " + trash);
            }
        } while (!done);

        return retVal;
    }


/**
 * Gets a double within an inclusive range.
 *
 * @param pipe Scanner used to read input
 * @param prompt Message displayed to the user
 * @param low Minimum accepted value
 * @param high Maximum accepted value
 * @return A double within the specified range
 */
public static double getRangedDouble(Scanner pipe, String prompt,
                                     double low, double high) {
    double retVal = 0;
    String trash = "";
    boolean done = false;

    do {
        System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

        if (pipe.hasNextDouble()) {
            retVal = pipe.nextDouble();
            pipe.nextLine();

            if (retVal >= low && retVal <= high) {
                done = true;
            } else {
                System.out.println("Value must be between " + low + " and " + high + ".");
            }
        } else {
            trash = pipe.nextLine();
            System.out.println("You must enter a double: " + trash);
        }
    } while (!done);

    return retVal;
} // Closes Method E
    /**
     * Gets a Yes or No confirmation.
     *
     * @param pipe Scanner used to read input
     * @param prompt Message displayed to the user
     * @return true for Yes, false for No
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine().trim();

            if (!response.equalsIgnoreCase("Y") &&
                    !response.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!response.equalsIgnoreCase("Y") &&
                !response.equalsIgnoreCase("N"));

        return response.equalsIgnoreCase("Y");
    }
    /**
     * Gets a String matching a regular expression.
     *
     * @param pipe Scanner used to read input
     * @param prompt Message displayed to the user
     * @param regEx Regular expression for validation
     * @return A String matching the pattern
     */
    public static String getRegExString(
            Scanner pipe, String prompt, String regEx) {

        String retString;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();

            if (retString.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!done);

        return retString;
    }
}      // Closes SafeInput class




