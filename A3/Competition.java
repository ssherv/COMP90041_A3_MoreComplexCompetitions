/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Creates an abstract Competition object -- provides a skeleton of a Competition which can be
 * change for a specific comeptition type via inheritance
 * @author Shervyn Singh
 */
public abstract class Competition implements Serializable {
    private String name; //competition name
    private int id; //competition identifier
    private String type; //competition type
    private boolean isActive; // competition status
    private boolean isTestingMode; //competition mode

    //ArrayList of Entry objects that are associated with a given Competition
    private ArrayList<Entry> listOfEntries = new ArrayList<Entry>();

    //ArrayList of Entry objects that are associated with a given Competition and have won some
    // sort of prize
    private ArrayList<Entry> winningEntries = new ArrayList<Entry>();


    /**
     * Default Constructor: Creates an empty Competition object
     */
    public Competition() {
    }


    /**
     * Creates a fully realised Competition object with all variables instantiated
     *
     * @param name the name of a particular competition
     * @param id the id of a particular competition
     * @param type the type of competition (type specifies which kind of competition to make)
     * @param isActive whether the competition is active of inactive (finished)
     * @param isTestingMode whether the competition is run in testing mode (determines winners
     *                      and entries give a seed value) or normal mode (random generation)
     */
    public Competition(String name, int id, String type, boolean isActive, boolean isTestingMode) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.isActive = isActive;
        this.isTestingMode = isTestingMode;
    }


    /**
     * Adds a given Entry into a competition
     * This method is the same for all competition types - hence implemented in the base class
     *
     * @param validEntry an Entry object to be added to a particular competition
     */
    public void addEntry(Entry validEntry) {
        this.listOfEntries.add(validEntry);
    }


    /**
     * Abstract method to draw competition winners
     * Specific method to draw winners depends on a competition type -- hence abstract method
     */
    public abstract void drawWinners();


    /**
     * Prints a report that summarises a given Competition once it has been completed
     */
    public void report() {
        String compStatus = "no"; //to convert from boolean false --> no
        if (this.getIsActive()) { //to convert from boolean true --> yes
            compStatus = "yes";
        }

        System.out.println("");
        System.out.println(
            "Competition ID: " + this.getId() + ", name: " + this.getName() + ", active: " + compStatus);
        System.out.println("Number of entries: " + this.listOfEntries.size());
        if (this.listOfEntries.size() != 0 && this.winningEntries.size() != 0) {
            System.out.println("Number of winning entries: " + this.winningEntries.size());

            int totalPrize = 0;
            for (Entry winningEntry : this.winningEntries) {
                totalPrize += winningEntry.getPrize();
            }
            System.out.println("Total awarded prizes: " + totalPrize);
        }
        return;
    }


    /**
     * Abstract method to print the winning report which lists winners of a Competition
     * Specific method to print winners depends on a competition type -- hence abstract method
     */
    public abstract void winnerReport(DataProvider dataprovider);


    /**
     * Prints attributes to describe a given Competition
     *
     * @return a String listing a Competition's attributes
     */
    public String toString() {
        return "Competition ID: " + id + ", Competition Name: " + name + ", Type: " + type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public boolean getIsTestingMode() {
        return isTestingMode;
    }

    public void setIsTestingMode(boolean testingMode) {
        isTestingMode = testingMode;
    }

    public ArrayList<Entry> getListOfEntries() {
        return listOfEntries;
    }

    public void setListOfEntries(ArrayList<Entry> listOfEntries) {
        this.listOfEntries = listOfEntries;
    }

    public ArrayList<Entry> getWinningEntries() {
        return winningEntries;
    }

    public void setWinningEntries(ArrayList<Entry> winningEntries) {
        this.winningEntries = winningEntries;
    }
}
