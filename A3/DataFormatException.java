/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

/**
 * User developed Exception -- specifically for if some data is formatted incorrectly
 * @author Shervyn Singh
 */
public class DataFormatException extends Exception {

  /**
   * Exception constrictor with default message
   */
  public DataFormatException() {
    super("Data format is incorrect.");
  }

  /**
   * Exception constructor with given message
   * @param message message given by programmer/user to be printed if the exception arises
   */
  public DataFormatException(String message) {
    super(message);
  }
}
