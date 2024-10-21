/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad1;



public class Main {
  public static void main(String[] args) {
    String filePath = "/Users/kamil/Desktop/UTP5_WK_S23878/Towary.txt";

    Thread threadA = new Thread(new ThreadA(filePath));
    Thread threadB = new Thread(new ThreadB(filePath));

    threadA.start();
    threadB.start();

    try {
      threadA.join();
      threadB.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
