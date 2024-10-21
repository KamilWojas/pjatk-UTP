/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad3;

public class Writer implements Runnable {
    private final Author author;

    public Writer(Author author) {
        this.author = author;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = generateMessage();
                author.addToQueue(message);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String generateMessage() {

        return "asia lubi psy";
    }
}