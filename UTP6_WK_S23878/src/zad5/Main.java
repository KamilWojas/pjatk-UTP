/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad5;


public class Main {
  public static void main(String[] args) {
    Author autor = new Author(args);
    new Thread(autor).start();
    new Thread(new Writer(autor)).start();
  }
}
