/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Creates automatic entries to be used in a given competition
 * Numbers can either be created at random or by using a seed
 * @author Shervyn Singh
 */
public class AutoNumbersEntry extends NumbersEntry {

  private final int NUMBER_COUNT = 7;
  private final int MAX_NUMBER = 35;


  /**
   * Default Constructor: Creates an empty AutoNumbersEntry object
   */
  public AutoNumbersEntry() {
  }


  /**
   * Overridden from Entry base class
   * Creates a fully-realised AutoNumbersEntry object using the super constructor
   *
   * @param entryID id of an individual entry
   * @param billID id of the bill associated with a particular entry
   * @param memberID id of the member associated with a given bill
   */
  public AutoNumbersEntry(int entryID, String billID, String memberID) {
    super(entryID, billID, memberID);
  }


  /**
   * Creates an integer array corresponding to automatically generated numbers for a given entry
   *
   * @param seed seed value which determines what numbers are printed -- a given seed will always
   *            generate the same subset of numbers
   * @return and integer array with 7 random numbers, sorted in ascending order
   */
  public int[] createNumbers(int seed) {
    ArrayList<Integer> validList = new ArrayList<Integer>();
    int[] tempNumbers = new int[NUMBER_COUNT];
    for (int i = 1; i <= MAX_NUMBER; i++) {
      validList.add(i);
    }
    Collections.shuffle(validList, new Random(seed));
    for (int i = 0; i < NUMBER_COUNT; i++) {
      tempNumbers[i] = validList.get(i);
    }
    Arrays.sort(tempNumbers);
    return tempNumbers;
  }


  /**
   * Prints a given Entry in a presentable format
   */
  public void printEntry() {
    System.out.printf("Entry ID: %-7d", getEntryId());
    printNumbers();
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
    System.out.println(" [Auto]");
  }
}






