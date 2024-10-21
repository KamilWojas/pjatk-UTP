/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad5;


import java.util.concurrent.ArrayBlockingQueue;

public class Author implements Runnable {
    private String[] arrArrayOfText;
    private int intNumOfWords;
    private ArrayBlockingQueue<String> arrbqTextsForWriter;

    Author(String[] arrInputTexts) {
        this.arrArrayOfText = arrInputTexts;
        this.intNumOfWords = this.arrArrayOfText.length;
        this.arrbqTextsForWriter = new ArrayBlockingQueue(this.intNumOfWords);
    }

    public void run() {
        for(int i = 0; i < this.intNumOfWords; ++i) {
            try {
                Thread.sleep(1000L);
                this.arrbqTextsForWriter.put(this.arrArrayOfText[i]);
            } catch (InterruptedException var3) {
            }
        }

    }

    public int intGetNumOfWords() {
        return this.intNumOfWords;
    }

    public ArrayBlockingQueue<String> getArrayBlockingQueue() {
        return this.arrbqTextsForWriter;
    }
}