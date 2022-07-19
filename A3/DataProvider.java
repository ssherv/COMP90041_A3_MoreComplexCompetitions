/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles all data provision requirements -- acts as the interface between a given database and
 * the program
 *
 * @author Shervyn Singh
 */
public class DataProvider {


  private String memberFile; //member file path
  private String billFile; //bill file path

  //ArrayList of Members -- read via provided csv files
  private ArrayList<Member> memberList = new ArrayList<Member>();

  //ArrayList of Bills -- read via provided csv files
  private ArrayList<Bill> billList = new ArrayList<Bill>();


  /**
   * Default Constructor: Creates an empty DataProvider object
   */
  public DataProvider() {
  }


  /**
   * Initialises the program database after several checks are completed All program function is
   * based on the information created by DataProvider
   *
   * @param memberPath A path to the member file (e.g., members.csv)
   * @param billPath   A path to the bill file (e.g., bills.csv)
   */
  public DataProvider(String memberPath, String billPath) {
    if (checkFileExtension(memberPath) && checkFileExtension(billPath)) {
      this.memberFile = memberPath;
      this.billFile = billPath;
      setMemberList();
      setBillList();

    } else {
      System.out.println("Files provided are in an incompatible format.");
      System.out.println("Exiting Program. Goodbye.");
      System.exit(0);
    }
  }


  /**
   * Checks if a file path is of the correct type (.csv or .txt)
   *
   * @param aFile the filepath/name of the data files
   * @return true if the file matches (.csv or .txt); else false
   */
  private boolean checkFileExtension(String aFile) {
    int index = aFile.lastIndexOf('.');
    String extension = "";
    if (index > 0) {
      extension = aFile.substring(index + 1);
    }
    return (extension.equalsIgnoreCase("csv") ||
        extension.equalsIgnoreCase("txt"));
  }


  /**
   * Reads member database file and checks for formatting issues. If all checks are successful --
   * creates an ArrayList of Members to be used when running the program
   */
  private void setMemberList() {
    File memberFile = new File(this.memberFile);

    try {
      Scanner scan = new Scanner(memberFile);

      while (scan.hasNextLine()) {
        String data = scan.nextLine();
        String[] values = data.split(",");
        String id = values[0];
        String name = values[1];
        String email = values[2];
        Member newMember = new Member(id, name, email);
        memberList.add(newMember);
      }
      scan.close();

    } catch (FileNotFoundException e) {
      System.out.println("Member file was not found. \nExiting Program. Goodbye.");
      System.exit(0);

    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      System.out.println("Member file is not formatted correctly. \nExiting Program. Goodbye.");
      System.exit(0);
    }
  }


  /**
   * Reads bill database file and checks for formatting issues. If all checks are successful --
   * creates an ArrayList of Bills to be used when running the program
   */
  private void setBillList() {
    File billFile = new File(this.billFile);

    try {
      Scanner scan = new Scanner(billFile);

      while (scan.hasNextLine()) {
        String data = scan.nextLine();
        String[] values = data.split(",");
        String id = values[0];
        String member = values[1];
        double total = Double.parseDouble(values[2]);
        boolean used = Boolean.parseBoolean(values[3]);
        Bill newBill = new Bill(id, member, total, used);
        billList.add(newBill);
      }
      scan.close();

    } catch (FileNotFoundException e) {
      System.out.println("Bill file was not found. \nExiting Program. Goodbye.");
      System.exit(0);

    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      System.out.println("Bill file is not formatted correctly. \nExiting Program. Goodbye.");
      System.exit(0);
    }
  }


  /**
   * Checks if a provided bill id passes all provided tests -- individual tests have their own
   * methods. If all tests successful, return index of the bill from the Bill ArrayList
   *
   * @param billID a String representing a given bill id -- provided by the user
   * @return integer index of an eligible bill from Bill ArrayList, else return -1 (if checks fail)
   */
  public int checkBill(String billID) {
    Bill billObject = new Bill();

    if (billObject.validBillID(billID)) {

      int billIndex = checkBillExists(billID);
      if (billIndex >= 0) {

        if (!billUsed(billIndex)) {
          String billMemberID = returnBillMemberID(billIndex);

          if (billMemberID != "") {

            if (checkForActiveMembership(billMemberID)) {
              return billIndex;
            } else {
              System.out.println("This bill is linked to a memberid that does not exist in the "
                  + "Member File. Please try again.");
            }
          } else {
            System.out.println("This bill has no member id. Please try again.");
          }
        } else {
          System.out
              .println("This bill has already been used for a competition. Please try again.");
        }
      } else {
        System.out.println("This bill does not exist. Please try again.");
      }
    }
    return -1;
  }


  /**
   * Checks if user-provided bill id exists in the billList
   *
   * @param billID bill id of a given bill
   * @return index of the bill if it exists in the billList, else -1 (if does not exist)
   */
  private int checkBillExists(String billID) {
    for (Bill bill : billList) {
      if (bill.getBillID().equalsIgnoreCase(billID)) {
        return billList.indexOf(bill);
      }
    }
    return -1;
  }


  /**
   * Returns a Bill object from the billList
   *
   * @param billIndex index of the bill to be retrieved
   * @return a particular bill
   */
  public Bill getBill(int billIndex) {
    return billList.get(billIndex);
  }


  /**
   * Returns the member id of the member associated with a particular bill
   *
   * @param index index of the bill to be checked
   * @return member id of a corresponding bill
   */
  private String returnBillMemberID(int index) {
    Bill ownedBill = billList.get(index);
    if (ownedBill.getBillMemberID() == null) {
      return "";
    } else {
      return ownedBill.getBillMemberID();
    }
  }


  /**
   * Checks if the member id associated with a given bill actually exists in the member database
   * (i.e. check if the member has a valid membership)
   *
   * @param memberID id of the member to be confirmed
   * @return true if member is exists; else false
   */
  private boolean checkForActiveMembership(String memberID) {
    for (Member member : memberList) {
      if (member.getMemberID().equalsIgnoreCase(memberID)) {
        return true;
      }
    }
    return false;
  }


  /**
   * Checks if a bill has been used in a previous competition
   *
   * @param index index of the bill to be checked (in billList)
   * @return true if bill has been used; else return false
   */
  private boolean billUsed(int index) {
    Bill ownedBill = billList.get(index);
    if (ownedBill.isBillUsed()) {
      return true;
    }
    return false;
  }


  /**
   * Update the 'used' status of a given bill (in the billList) to 'TRUE' if it has been used in a
   * competition
   *
   * @param billID bill id of the bill to be updated
   */
  public void setBillUsed(String billID) {
    int billIndex = checkBillExists(billID);
    Bill usedBill = billList.get(billIndex);
    usedBill.setBillUsed(true);
  }


  /**
   * Return the name of a given member from the memberList
   *
   * @param memberID member id to be checked
   * @return name associated with a given member id
   */
  public String getMemberName(String memberID) {
    for (Member m : memberList) {
      if (m.getMemberID().equalsIgnoreCase(memberID)) {
        return m.getMemberName();
      }
    }
    return "";
  }


  /**
   * Overwrite currently existing bill file to keep data files consistent (i.e. if the bill was
   * used in a competition - it should not be able to be used for any future competition)
   */
  public void updateBillFile() {

    try {
      FileWriter writer = new FileWriter(billFile);

      for (Bill b : billList) {

        String id = b.getBillID();
        String memberid = b.getBillMemberID();
        String total = String.valueOf(b.getBillTotal());
        String used = String.valueOf(b.isBillUsed()).toUpperCase();

        String s = id + "," + memberid + "," + total + "," + used;
        writer.write(s + "\n");
      }
      writer.close();

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }


  public String getMemberFile() {
    return memberFile;
  }

  public void setMemberFile(String memberFile) {
    this.memberFile = memberFile;
  }

  public String getBillFile() {
    return billFile;
  }

  public void setBillFile(String billFile) {
    this.billFile = billFile;
  }

  public ArrayList<Member> getMemberList() {
    return memberList;
  }

  public void setMemberList(ArrayList<Member> memberList) {
    this.memberList = memberList;
  }

  public ArrayList<Bill> getBillList() {
    return billList;
  }

  public void setBillList(ArrayList<Bill> billList) {
    this.billList = billList;
  }
}