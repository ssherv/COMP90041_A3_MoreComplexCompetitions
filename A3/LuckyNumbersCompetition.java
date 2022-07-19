/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Create a competition of type LuckyNumbers -- child class of Competition that adds additional
 * functionality
 * @author Shervyn Singh
 */
public class LuckyNumbersCompetition extends Competition {
  private final int FIRST_PRIZE = 50000;
  private final int SECOND_PRIZE = 5000;
  private final int THIRD_PRIZE = 1000;
  private final int FOURTH_PRIZE = 500;
  private final int FIFTH_PRIZE = 100;
  private final int SIXTH_PRIZE = 50;
  private final int[] prizes = {SIXTH_PRIZE, FIFTH_PRIZE, FOURTH_PRIZE,
      THIRD_PRIZE, SECOND_PRIZE, FIRST_PRIZE};

  // integer Array to store the winning Competition numbers (i.e. win the 1st Prize)
  private int[] winnerNumbers;


  /**
   * Default Constructor: Creates an empty LuckyNumbersCompetition object
   */
  public LuckyNumbersCompetition() {
  }


  /**
   * Creates a fully realised LuckyNumbersCompetition object with all variables instantiated --
   * utilises the Super Constructor / Constructor of the Parent Class (Competition)
   *
   * @param name the name of a particular competition
   * @param id the id of a particular competition
   * @param type the type of competition (type specifies which kind of competition to make)
   * @param isActive whether the competition is active of inactive (finished)
   * @param isTestingMode whether the competition is run in testing mode (determines winners
   *                      and entries give a seed value) or normal mode (random generation)
   */
  public LuckyNumbersCompetition(String name, int id, String type, boolean isActive,
      boolean isTestingMode) {
    super(name, id, type, isActive, isTestingMode);
  }


  /**
   * Returns a given Entry object from the list of entries associated with a particular
   * LuckyNumbersCompetition
   *
   * @param index index of the entry to be retrieved
   * @return Entry object at the associated index
   */
  private Entry getEntry(int index) {
    return this.getListOfEntries().get(index);
  }


  /**
   * Draw winners specific to LuckyNumbersCompetition by checking through each entry, comparing
   * it digit-by-digit to the winning numbers, and determining what prize each entry is
   * eligible to receive
   */
  public void drawWinners() {
    ArrayList<String> winningMemberIds = new ArrayList<String>();
    ArrayList<Entry> winningEntries = this.getWinningEntries();
    winnerNumbers = new AutoNumbersEntry().createNumbers(this.getId());

    if (!this.getIsTestingMode()) {
      winnerNumbers = new AutoNumbersEntry().createNumbers((int) (Math.random() * (1001)));
    }

    // Iterate through every entry
    for (int i = 0; i < this.getListOfEntries().size(); i++) {
      // Current iteration entry number
      Entry currentEntry = this.getEntry(i);
      // Gets entry numbers
      int[] entryNumbers = currentEntry.getNumbers();
      // Get MemberId for a particular entry
      String memberId = currentEntry.getMemberId();
      // Count the number of digits that match between entry and winner
      int numberOfMatches = 0;
      // For every number in winning numbers (luckyWinner)
      for (int j : winnerNumbers) {
        // Check if entryNumbers contains a winning number
        if (this.contains(entryNumbers, j)) {
          // increment numberOfMatches by one
          numberOfMatches += 1;
        }
      }

      // return what prize they get
      int prize = 0;
      if (numberOfMatches >= 2) {
        prize = prizes[numberOfMatches - 2];
      }
      if (prize > 0) {
        int indexOfMember = winningMemberIds.indexOf(memberId);
        currentEntry.setPrize(prize);
        if (indexOfMember == -1) {
          winningMemberIds.add(memberId);
          winningEntries.add(currentEntry);
        } else {
          Entry previousPrizeEntry = winningEntries.get(indexOfMember);
          int previousPrize = previousPrizeEntry.getPrize();
          if (previousPrize < prize) {
            winningEntries.set(indexOfMember, currentEntry);
          }
        }
      }
    }
  }


  /**
   * Checks whether an integer array contains a particular integer value
   *
   * @param arr integer array to be checked for a given value
   * @param value integer value to be looked up
   * @return true if array contains the value; else false
   */
  private boolean contains(int[] arr, int value) {
    for (int i : arr) {
      if (i == value) {
        return true;
      }
    }
    return false;
  }


  /**
   * Prints the winner report for LuckyNumbersCompetition in an easily readable format
   * Overridden from Competition base-class
   *
   * @param dataProvider the database information that is stored in the program
   */
  public void winnerReport(DataProvider dataProvider) {
    System.out.println(this);
    printLuckyNumbers();
    Collections.sort(this.getWinningEntries(), new PrizeComparator());
    System.out.println("Winning entries:");
    for (Entry e : this.getWinningEntries()) {
      System.out.printf("Member ID: " + e.getMemberId() + ", Member Name: " +
              dataProvider.getMemberName(e.getMemberId()) + ", Prize: %-5s", e.getPrize());
      System.out.println();
      System.out.print("--> Entry ID: " + e.getEntryId() + ", ");
      e.printNumbers();
    }
  }


  /**
   * Prints the winning numbers for a given Competition
   */
  private void printLuckyNumbers () {
    int[] numList = this.winnerNumbers;

    System.out.printf("Lucky Numbers:");
    for (int i = 0; i < numList.length; i++) {
      System.out.printf("%3d", numList[i]);
    }
    System.out.println(" [Auto]");
  }


  public int[] getWinnerNumbers() {
    return winnerNumbers;
  }

  public void setWinnerNumbers(int[] winnerNumbers) {
    this.winnerNumbers = winnerNumbers;
  }


}

