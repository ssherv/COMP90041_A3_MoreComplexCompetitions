/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */


import java.util.Collections;
import java.util.Random;

/**
 * Create a competition of type RandomPick -- child class of Competition that adds additional
 * functionality
 *
 * @author Shervyn Singh
 */
public class RandomPickCompetition extends Competition {

  private final int FIRST_PRIZE = 50000;
  private final int SECOND_PRIZE = 5000;
  private final int THIRD_PRIZE = 1000;
  private final int[] prizes = {FIRST_PRIZE, SECOND_PRIZE, THIRD_PRIZE};

  private final int MAX_WINNING_ENTRIES = 3;

  /**
   * Default Constructor: Creates an empty LuckyNumbersCompetition object
   */
  public RandomPickCompetition() {
  }

  /**
   * Creates a fully realised RandomPick object with all variables instantiated -- utilises the
   * Super Constructor / Constructor of the Parent Class (Competition)
   *
   * @param name          the name of a particular competition
   * @param id            the id of a particular competition
   * @param type          the type of competition (type specifies which kind of competition to
   *                      make)
   * @param isActive      whether the competition is active of inactive (finished)
   * @param isTestingMode whether the competition is run in testing mode (determines winners and
   *                      entries give a seed value) or normal mode (random generation)
   */
  public RandomPickCompetition(String name, int id, String type, boolean isActive,
      boolean isTestingMode) {
    super(name, id, type, isActive, isTestingMode);
  }

  /**
   * Draw winners specific to RandomPickCompetition by randomly choosing 3 entries to win the 3
   * available prizes. If a user has won - does not give them any additional prize.
   */
  public void drawWinners() {

    Random randomGenerator = null;

    if (this.getIsTestingMode()) {
      randomGenerator = new Random(this.getId());
    } else {
      randomGenerator = new Random();
    }

    int winningEntryCount = 0;
    while (winningEntryCount < MAX_WINNING_ENTRIES) {

      int winningEntryIndex = randomGenerator.nextInt(this.getListOfEntries().size());

      Entry winningEntry = this.getListOfEntries().get(winningEntryIndex);
      String winningMemberID = winningEntry.getMemberId();

      if (hasMemberWon(winningMemberID)) {
        winningEntryCount++;
      }

      if (!hasMemberWon(winningMemberID)) {

        if (winningEntry.getPrize() == 0) {
          int currentPrize = prizes[winningEntryCount];
          winningEntry.setPrize(currentPrize);
          this.getWinningEntries().add(winningEntry);

          winningEntryCount++;
        }
      }
    }
  }

  /**
   * Checks if a winning entry is associated with a Member which has already won a prize (via one of
   * their other entries)
   *
   * @param memberID member id to be checked
   * @return true if member already has a winning entry; else false
   */
  private boolean hasMemberWon(String memberID) {
    for (Entry e : this.getWinningEntries()) {
      if (e.getMemberId().equalsIgnoreCase(memberID)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Prints the winner report for LuckyNumbersCompetition in an easily readable format Overridden
   * from Competition base-class
   *
   * @param dataProvider the database information that is stored in the program
   */
  public void winnerReport(DataProvider dataProvider) {
    System.out.println(this);
    System.out.println("Winning entries:");
    Collections.sort(this.getWinningEntries(), new PrizeComparator());
    for (Entry e : this.getWinningEntries()) {
      System.out.printf("Member ID: " + e.getMemberId() + ", Member Name: " + dataProvider
          .getMemberName(e.getMemberId()) +
          ", Entry ID: " + e.getEntryId() + ", Prize: %-5s", e.getPrize());
      System.out.println("");
    }
  }
}

