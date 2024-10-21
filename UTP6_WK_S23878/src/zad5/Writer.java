/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad5;




public class Writer implements Runnable {
    Author autAuthor;

    Writer(Author author) {
        this.autAuthor = author;
    }

    public void run() {
        for(int i = 1; i <= this.autAuthor.intGetNumOfWords(); ++i) {
            try {
                System.out.println("Napis " + i + ": " + (String)this.autAuthor.getArrayBlockingQueue().take());
            } catch (InterruptedException var3) {
            }
        }

    }
}
