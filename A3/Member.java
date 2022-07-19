/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

import java.util.ArrayList;

/**
 * Defines Member objects and their instance variable
 * @author Shervyn Singh
 */
public class Member {

  private String memberID; //member identifier
  private String memberName; //member name
  private String memberEmail; //member email address


  /**
   * Default Constructor: Creates an empty Member object
   */
  Member() {
  }


  /**
   * Creates a fully-realised member object with all variables instantiated
   *
   * @param id member id to identify a particular member
   * @param name name associated with a member id
   * @param email email address associated with a member id
   */
  Member(String id, String name, String email) {
    this.memberID = id;
    this.memberName = name;
    this.memberEmail = email;
  }


  /**
   * String representation of a member's details
   * @return member details in formatted form
   */
  public String toString() {
    return "ID: " + memberID + " NAME: " + memberName + " EMAIL: " + memberEmail;
  }

  public String getMemberID() {
    return memberID;
  }

  public void setMemberID(String memberID) {
    this.memberID = memberID;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getMemberEmail() {
    return memberEmail;
  }

  public void setMemberEmail(String memberEmail) {
    this.memberEmail = memberEmail;
  }

}
