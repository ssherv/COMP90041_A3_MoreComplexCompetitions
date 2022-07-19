/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */


/**
 * Defines Bill objects and includes methods to check for compliance to competition requirements
 * @author Shervyn Singh
 */
public class Bill {

  private String billID; //bill identifier
  private String billMemberID; //member id of a given bill
  private Double billTotal; //total of a given bill
  private boolean billUsed; //check whether bill has been used for a previous competition

  // Determined by company competition policy
  private final int BILL_ID_MAX_DIGITS = 6;
  private final double MINIMUM_BILL_AMOUNT = 50.0;


  /**
   * Default Constructor: Creates an empty Bill object
   */
  Bill() {
  }


  /**
   * Creates a fully-realised bill object with all variables instantiated
   *
   * @param id id of a given bill object - obtained from a bill file (e.g. bills.csv)
   * @param memberID id of the member associated with a given bill
   * @param total the total dollar amount of the bill
   * @param used false if a given bill has not been used in a competition before; else return true
   */
  Bill(String id, String memberID, double total, boolean used) {
    this.billID = id;
    this.billMemberID = memberID;
    this.billTotal = total;
    this.billUsed = used;
  }


  /**
   * Checks if a given Bill ID matches competition requirements
   *
   * @param billID a given bill id - to be checked for adherence to competition requirements
   * @return true if bill id passes all checks; else return false
   */
  public boolean validBillID(String billID) {
    if ((billID.length() == BILL_ID_MAX_DIGITS) && (billID.matches("[0-9 ]+"))) {
      return true;
    } else {
      System.out.println("Invalid bill id! It must be a 6-digit number. Please try again.");
      return false;
    }
  }


  /**
   * Calculates the number of eligible entries that can be entered into a competition
   *
   * @return integer count of eligible entries
   */
  public int numEntries() {
    int numEntries = (int) (billTotal / MINIMUM_BILL_AMOUNT);

    if (numEntries == 0) {
      System.out.println("This bill is not eligible for an entry. The total amount is smaller "
          + "than $" + MINIMUM_BILL_AMOUNT + "!");
      return numEntries;
    }
    System.out.print("This bill ($" +billTotal+ ") is eligible for " +numEntries+ " entries.");
    return numEntries;
  }


  /**
   * Prints attributes to describe a given Bill object
   *
   * @return a String listing a bill's attributes
   */
  public String toString() {
    return "ID: " + billID + " MEMBER: " + billMemberID + " TOTAL: " + billTotal + " USED? " + billUsed;
  }


  public String getBillID() {
    return billID;
  }

  public void setBillID(String billID) {
    this.billID = billID;
  }

  public String getBillMemberID() {
    return billMemberID;
  }

  public void setBillMemberID(String billMemberID) {
    this.billMemberID = billMemberID;
  }

  public Double getBillTotal() {
    return billTotal;
  }

  public void setBillTotal(Double billTotal) {
    this.billTotal = billTotal;
  }

  public boolean isBillUsed() {
    return billUsed;
  }

  public void setBillUsed(boolean billUsed) {
    this.billUsed = billUsed;
  }
}
