/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Author implements Runnable {
    private final BlockingQueue<String> queue;

    public Author(String[] messages) {
        queue = new LinkedBlockingQueue<>();

        for (String message : messages) {
            try {
                queue.put(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void addToQueue(String message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = queue.poll(1000, java.util.concurrent.TimeUnit.MILLISECONDS);
                if (message != null) {
                    System.out.println("Author: " + message);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
