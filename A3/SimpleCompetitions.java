/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class that runs the entire program Includes helper functions alongside normal program
 * execution
 *
 * @author Shervyn Singh
 */
public class SimpleCompetitions {

  // Scanner available to all classes
  public static Scanner keyboard = new Scanner(System.in);

  // Records the Program Execution Mode (Testing --> True, Normal --> False)
  // Instantiated as true to prevent any compilation errors -- program state altered via normal
  // program function
  private boolean isTestingMode = true;

  // Database for program execution -- access copies of data files through this class
  DataProvider programData;

  // Initialise ArrayList of Competitions -- records all competitions made during execution
  private ArrayList<Competition> listOfCompetitions = new ArrayList<Competition>();


  /**
   * Initialise how the instance of SimpleCompetitions is to be run Allows users to load program
   * from a binary file if they want to continue previous execution
   */
  private void initialiseProgram() {
    while (true) {
      System.out.println("Load competitions from file? (Y/N)?");

      char loadChoice = keyboard.next().toUpperCase().charAt(0);

      if (loadChoice == 'N') {
        chooseMode();
        break;

      } else if (loadChoice == 'Y') {
        System.out.println("File name:");
        String path = keyboard.next();
        loadExisting(path);
        break;

      } else {
        System.out.println("Unsupported option. Please try again!");
      }
    }
    String memberPath = memberFileInput();
    String billPath = billFileInput();
    programData = new DataProvider(memberPath, billPath);
  }


  /**
   * Allows users to choose which mode they want to use -- either Testing or Normal
   *
   * @return true if testing mode; else false if normal
   */
  private boolean chooseMode() {
    while (true) {
      System.out.println("Which mode would you like to run? "
          + "(Type T for Testing, and N for Normal mode):");

      char modeChoice = keyboard.next().toUpperCase().charAt(0);

      if (modeChoice == 'N') {
        return isTestingMode = false;
      } else if (modeChoice == 'T') {
        return isTestingMode = true;
      } else {
        System.out.println("Invalid mode! Please choose again.");
      }
    }
  }


  /**
   * Asks user to load the member database file -- via a file path
   *
   * @return String representation of the path/file name
   */
  private String memberFileInput() {
    System.out.println("Member file: ");
    String memberFile = keyboard.next();
    keyboard.nextLine();
    return memberFile;
  }


  /**
   * Asks user to load the bill database file -- via a file path
   *
   * @return String representation of the path/file name
   */
  private String billFileInput() {
    System.out.println("Bill file: ");
    String billFile = keyboard.next();
    keyboard.nextLine();
    return billFile;
  }


  /**
   * Checks whether there is an active competition that is currently running
   *
   * @return true if active competition; else return false
   */
  private boolean checkStatus() {
    for (Competition status : listOfCompetitions) {
      if (status.getIsActive()) {
        return true;
      }
    }
    return false;
  }


  /**
   * Allows users to create a new competition -- first checks if there is already an active
   * competition that is currently running
   *
   * @return newly-made Competition object
   */
  private Competition addNewCompetition() {
    while (true) {

      if (checkStatus()) {
        System.out.println("There is an active competition. SimpleCompetitions does not "
            + "support concurrent competitions!");
        return null;

      } else {
        System.out.println("Type of competition (L: LuckyNumbers, R: RandomPick)?:");

        char competitionType = keyboard.next().toUpperCase().charAt(0);
        keyboard.nextLine();

        if (competitionType == 'L') {
          System.out.println("Competition name: ");
          String compName = keyboard.nextLine();
          int compID = listOfCompetitions.size() + 1;
          String type = "LuckyNumbersCompetition";
          boolean status = true;
          boolean programMode = isTestingMode;

          LuckyNumbersCompetition newCompetition = new LuckyNumbersCompetition(compName, compID,
              type, status, programMode);

          listOfCompetitions.add(newCompetition);
          System.out.println("A new competition has been created!");
          System.out.println(newCompetition);
          return newCompetition;

        } else if (competitionType == 'R') {
          System.out.println("Competition name: ");
          String compName = keyboard.nextLine();
          int compID = listOfCompetitions.size() + 1;
          String type = "RandomPickCompetition";
          boolean status = true;
          boolean programMode = isTestingMode;

          RandomPickCompetition newCompetition = new RandomPickCompetition(compName, compID,
              type, status, programMode);

          listOfCompetitions.add(newCompetition);
          System.out.println("A new competition has been created!");
          System.out.println(newCompetition);
          return newCompetition;

        } else {
          System.out.println("Invalid competition type! Please choose again.");
        }
      }
    }
  }


  /**
   * Allows users to enter a bill ID -- if the bill id passes various checks, signify it as eligible
   * (i.e. it can be used in the current competition)
   *
   * @return an eligible bill object
   */
  private Bill enterBill() {
    while (true) {
      System.out.println("Bill ID: ");
      String billID = keyboard.next();
      keyboard.nextLine();
      int billIndex = programData.checkBill(billID);
      if (billIndex >= 0) {
        Bill eligibleBill = programData.getBill(billIndex);
        return eligibleBill;
      }
    }
  }

  /**
   * Asks users if they want to add more entries -- controls the program loop
   *
   * @return user choice as a character --> 'Y' for continue, 'N' for stop
   */
  private char addMoreEntries() {
    while (true) {
      System.out.println("Add more entries (Y/N)?");
      char choice = keyboard.next().toUpperCase().charAt(0);

      if (choice == 'N') {
        return 'N';

      } else if (choice == 'Y') {
        return 'Y';

      } else {
        System.out.println("Unsupported option. Please try again!");
      }
    }
  }

  /**
   * Allows the user to close the program and save program execution and files if they want
   */
  private void closeProgram() {
    while (true) {
      System.out.println("Save competitions to file? (Y/N)?");

      char choice = keyboard.next().toUpperCase().charAt(0);

      if (choice == 'N') {
        break;

      } else if (choice == 'Y') {
        System.out.println("File name:");
        String path = keyboard.next();

        saveExisting(path);
        System.out.println("Competitions have been saved to file.");

        programData.updateBillFile();
        System.out.println("The bill file has also been automatically updated.");
        break;

      } else {
        System.out.println("Unsupported option. Please try again!");
      }
    }
  }

  /**
   * Prints a summary report of SimpleCompetitions program execution
   * Shows how many competitions have been made and what the outcome is
   */
  public void report() {
    System.out.println("----SUMMARY REPORT----");

    int completedCompetitions = 0;
    int activeCompetitions = 0;

    for (Competition comp : this.listOfCompetitions) {
      if (comp.getIsActive()) {
        activeCompetitions += 1;
      } else {
        completedCompetitions += 1;
      }
    }
    System.out.println("+Number of completed competitions: " + completedCompetitions);
    System.out.println("+Number of active competitions: " + activeCompetitions);

    for (Competition comp : this.listOfCompetitions) {
      comp.report();
    }
  }

  /**
   * Allows users to load a binary file to continue execution of an instance of SimpleCompetitions
   * @param path filepath/name of the binary file
   */
  private void loadExisting(String path) {

    try {
      FileInputStream fis = new FileInputStream(path);
      ObjectInputStream ois = new ObjectInputStream(fis);
      listOfCompetitions = (ArrayList<Competition>) ois.readObject();
      ois.close();
      fis.close();
    } catch (IOException e) {
      System.out.println("Binary file not found. Exiting Program. \nGoodbye!");
      System.exit(0);
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found. Exiting Program. \nGoodbye!");
      System.exit(0);
    }
  }


  /**
   * Allows users to save an instance of SimpleCompetitions to a binary file for continued used
   * @param path filepath/name of the binary file
   */
  private void saveExisting(String path) {

    try {
      FileOutputStream fos = new FileOutputStream(path);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(listOfCompetitions);
      oos.close();
      fos.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private void numeralCheck() {
    while (true) {

    }
  }

  /**
   * Primary program loop -- determines how the SimpleCompetitions program will execute based on
   * user choices
   */
  private void programLoop() {
    while (true) {
      System.out.println("Please select an option. Type 5 to exit.");
      System.out.println("1. Create a new competition");
      System.out.println("2. Add new entries");
      System.out.println("3. Draw winners");
      System.out.println("4. Get a summary report");
      System.out.println("5. Exit");

      try {
        int userChoice = Integer.parseInt(keyboard.next());

        switch (userChoice) {
          case 1:
            addNewCompetition();
            break;

          case 2:
            if (!checkStatus()) {
              System.out.println("There is no active competition. Please create one!");
              break;
            }

            char addEntries = 'Y';

            while (addEntries == 'Y') {
              Competition activeComp = listOfCompetitions.get(listOfCompetitions.size() - 1);
              int entryID = activeComp.getListOfEntries().size() + 1;
              int entryIndex = activeComp.getListOfEntries().size();
              Bill eligibleBill = enterBill();
              int totalEntries = eligibleBill.numEntries();

              // Logic to add Entries for LuckyNumberCompetition
              if (totalEntries > 0 && activeComp.getType().equalsIgnoreCase(
                  "LuckyNumbersCompetition")) {
                System.out.println(" How many manual entries did the customer fill up?: ");

                while (true) {
                  int manualEntries = Integer.parseInt(keyboard.next());
                  keyboard.nextLine();
                  int autoEntries = totalEntries - manualEntries;

                  if (manualEntries > totalEntries) {
                    System.out.println("The number must be in the range from 0 to " + totalEntries
                        + ". Please try again.");

                  } else if (manualEntries >= 0) {

                    while (manualEntries > 0) {
                      NumbersEntry manualEntry = new NumbersEntry(entryID,
                          eligibleBill.getBillID(), eligibleBill.getBillMemberID());
                      manualEntry.manualEntryPrompt();
                      String entryString = keyboard.nextLine();
                      int[] entryInt = manualEntry.validManualEntries(entryString);

                      if (entryInt != null) {
                        Arrays.sort(entryInt);
                        manualEntry.setNumbers(entryInt);
                        activeComp.addEntry(manualEntry);
                        entryID += 1;
                        manualEntries -= 1;
                      }
                    }

                    while (autoEntries > 0) {
                      AutoNumbersEntry autoEntry = new AutoNumbersEntry(entryID,
                          eligibleBill.getBillID(),
                          eligibleBill.getBillMemberID());
                      int seedValue = activeComp.getListOfEntries().size();

                      if (!activeComp.getIsTestingMode()) {
                        seedValue = (int) (Math.random() * (1001));
                      }

                      int[] entryInt = autoEntry.createNumbers(seedValue);
                      autoEntry.setNumbers(entryInt);
                      activeComp.addEntry(autoEntry);
                      entryID += 1;
                      autoEntries -= 1;
                    }

                    System.out.println("The following entries have been added:");
                    for (int i = entryIndex; i < activeComp.getListOfEntries().size(); i++) {
                      Entry entry = activeComp.getListOfEntries().get(i);
                      entry.printEntry();
                    }
                    break;
                  }
                }
              }

              // Logic to add Entries for RandomPickCompetition
              if (totalEntries > 0 && activeComp.getType().equalsIgnoreCase(
                  "RandomPickCompetition")) {
                for (int i = 1; i <= totalEntries; i++) {
                  Entry rpEntry = new Entry(entryID, eligibleBill.getBillID(),
                      eligibleBill.getBillMemberID());
                  entryID += 1;
                  activeComp.addEntry(rpEntry);
                }
                System.out.println("\nThe following entries have been automatically generated:");
                for (int i = entryIndex; i < activeComp.getListOfEntries().size(); i++) {
                  Entry entry = activeComp.getListOfEntries().get(i);
                  entry.printEntry();
                }
              }

              //Changes status of a bill to used -- so it cannot be used for another competition
              String usedBill = eligibleBill.getBillID();
              programData.setBillUsed(usedBill);

              addEntries = addMoreEntries();
            }
            break;

          case 3:
            if (!checkStatus()) {
              System.out.println("There is no active competition. Please create one!");
              break;
            }

            Competition activeComp = listOfCompetitions.get(listOfCompetitions.size() - 1);
            if (activeComp.getListOfEntries().size() == 0) {
              System.out.println("The current competition has no entries yet!");
              break;
            }

            activeComp.drawWinners();
            activeComp.winnerReport(programData);
            activeComp.setIsActive(false);
            break;

          case 4:
            if (listOfCompetitions.size() == 0) {
              System.out.println("No competition has been created yet!");
              break;
            }
            this.report();
            break;

          case 5:
            closeProgram();
            System.out.println("Goodbye!");
            System.exit(0);

          default:
            System.out.println("Unsupported option. Please try again!");
        }
      } catch (NumberFormatException e) {
        System.out.println("A number is expected. Please try again.");
      } catch (Exception e) {
        System.out.println("A number is expected. Please try again.");
      }
    }
  }


  /**
   * Main program that uses the main SimpleCompetitions class
   *
   * @param args main program arguments
   */
  public static void main(String[] args) {

    SimpleCompetitions sc = new SimpleCompetitions();

    System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");

    sc.initialiseProgram(); //TODO figure out file loading

    sc.programLoop();
  }
}
