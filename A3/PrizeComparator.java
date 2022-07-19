import java.util.Comparator;

/**
 * Comparator class for Entry objects
 * @author Shervyn Singh
 */
class PrizeComparator implements Comparator<Entry> {

  /**
   * Compares two entry objects to determine which was was entered first (i.e. which entry has
   * the lowest entryID)
   *
   * @param e1 entry 1
   * @param e2 entry 2
   * @return -1, 0, or 1 if the first object (e1) is less than, equal to, or greater than the
   * second object (e2)
   */
  public int compare(Entry e1, Entry e2) {
    if(e1.getEntryId() == e2.getEntryId()) {
      return 0;
    } else if (e1.getEntryId() < e2.getEntryId()) {
      return -1;
    } else {
      return 1;
    }
  }
}
