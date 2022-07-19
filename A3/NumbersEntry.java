/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.util.Arrays;

/**
 * Creates manual entry objects - to be used in a given competition
 * Numbers are manually chosen by a member and input into the system by the user
 * @author Shervyn Singh
 */
public class NumbersEntry extends Entry {
    private final int NUMBER_COUNT = 7;
    private final int MAX_NUMBER = 35;


    /**
     * Default Constructor: Creates an empty NumberEntry object
     */
    public NumbersEntry() {
    }


    /**
     * Overridden from Entry base class
     * Creates a fully-realised NumberEntry object using the super constructor
     *
     * @param entryID id of an individual entry
     * @param billID id of the bill associated with a particular entry
     * @param memberID id of the member associated with a given bill
     */
    public NumbersEntry(int entryID, String billID, String memberID) {
        super(entryID, billID, memberID);
    }


    /**
     * Prompt users to enter their manual entry -- number of digits and max number choice
     * constrained by competition policy
     */
    public void manualEntryPrompt() {
        System.out.println("Please enter " + NUMBER_COUNT + " different numbers (from the "
            + "range 1 to " + MAX_NUMBER + ") separated by whitespace.");
    }


    /**
     * Checks whether the user input manual entry meets competition requirements (i.e. only
     * digits, of the correct size, within the given range). Individual tests provided via more
     * specified sub-methods
     *
     * @param entryString String representation of the manual entry (e.g. "1 2 3 4 5 6 7")
     * @return integer Array with the manual entry if all checks are successful
     */
    public int[] validManualEntries(String entryString) {
        if (entryString.matches("[0-9 ]+")) {

            String[] arrOfEntryString = entryString.split(" ");

            if (arrOfEntryString.length < NUMBER_COUNT) {
                System.out.println("Invalid input! Fewer than " + NUMBER_COUNT + " numbers are "
                    + "provided. Please try again!");
                return null;

            } else if (arrOfEntryString.length > NUMBER_COUNT) {
                System.out.println("Invalid input! More than " + NUMBER_COUNT + " numbers are "
                    + "provided. Please try again!");
                return null;

            } else if (arrOfEntryString.length == NUMBER_COUNT) {

                if (containsDuplicates(arrOfEntryString) == true) {
                    System.out.println("Invalid input! All numbers must be different!");
                    return null;

                } else if (containsDuplicates(arrOfEntryString) == false) {

                    if (maxValue(arrOfEntryString) <= MAX_NUMBER) {
                        int[] arrOfEntryInt = new int[NUMBER_COUNT];
                        for (int b = 0; b < arrOfEntryString.length; b++) {
                            arrOfEntryInt[b] = Integer.parseInt(arrOfEntryString[b]);
                        }
                        return arrOfEntryInt;
                    }
                    else {
                        System.out.println("Invalid input! All numbers must be in the range from 1 to "
                            + MAX_NUMBER + "!");
                    }
                }
            }
        } else {
            System.out.println("Invalid input! Numbers are expected. Please try again!");
        }
        return null;
    }


    /**
     * Check whether the manually input entry numbers contain any duplicates
     *
     * @param aString manual entry as a String array - each digit is 1 element of the array
     * @return true if duplicates found; else false
     */
    private boolean containsDuplicates(String[] aString) {
        for (int i = 0; i < aString.length; i++) {
            for (int j = i + 1; j < aString.length; j++) {
                if (aString[i].equals(aString[j])) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Finds the biggest number that the user input for their manual entry
     *
     * @param aString manual entry as a String array - each digit is 1 element of the array
     * @return the largest number provided - as an integer
     */
    private int maxValue(String[] aString) {
        int max = Integer.parseInt(aString[0]);

        for (int i = 1; i < aString.length; i++) {
            if (Integer.parseInt(aString[i]) > max) {
                max = Integer.parseInt(aString[i]);
                }
            }
        return max;
        }


    /**
     * Prints just the Entry numbers in a given format -- to show the program user what the
     * automatically generated numbers are
     */
    public void printNumbers() {
        int[] numList = getNumbers();

        System.out.printf("Numbers:");
        for (int i = 0; i < numList.length; i++) {
            System.out.printf("%3d", numList[i]);
        }
        System.out.println("");
    }


    /**
     * Prints a given Entry in a presentable format
     */
    public void printEntry() {
        System.out.printf("Entry ID: %-7d", getEntryId());
        printNumbers();
    }
}


