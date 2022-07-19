/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.io.Serializable;

/**
 * Base Class to create generic Entry Objects
 * @author Shervyn Singh
 */
public class Entry implements Serializable {
    private int entryId; //entry identifier
    private String billId; //bill identifier
    private String memberId; //member identifier
    private int prize; //a given prize (dependent on competition type)
    private int[] numbers; //entry numbers


    /**
     * Default Constructor: Creates an empty Entry object
     */
    public Entry() {}


    /**
     * Creates a generic Entry object -- inherited entry types use this as a skeleton
     * @param entryID id of a given entry
     * @param billID id of a bill linked to a particular entry
     * @param memberID id of the member that is linked to a particular bill - and hence entry
     */
    public Entry(int entryID, String billID, String memberID) {
        this.entryId = entryID;
        this.billId = billID;
        this.memberId = memberID;
    }


    /**
     * Print the entry ID in a formatted manner
     */
    public void printEntry() {
        System.out.printf("Entry ID: %-6s", getEntryId());
        System.out.println("");
    }


    /**
     * Base numberPrint class - overridden by inherited classes
     */
    public void printNumbers() {
    }


    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}
