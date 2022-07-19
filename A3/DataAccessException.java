/*
 * Student name: Shervyn Singh
 * Student ID: 1236509
 * LMS username: shervyns
 */

/**
 * User developed Exception -- specifically for if some data cannot be correctly accessed or
 * retrieved --> alternative to FileNotFoundException
 * @author Shervyn Singh
 */
public class DataAccessException extends Exception {

  /**
   * Exception constrictor with default message
   */
  public DataAccessException() {
    super("File cannot be opened or read.");
  }

  /**
   * Exception constructor with given message
   * @param message message given by programmer/user to be printed if the exception arises
   */
  public DataAccessException(String message) {
    super(message);
  }
}
