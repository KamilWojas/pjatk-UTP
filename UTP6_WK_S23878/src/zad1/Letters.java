/**
 *
 *  @author Wojas Kamil S23878
 *
 */



package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    List<Thread> threadArrayList = new ArrayList<>();
    private static final int sleep = 1000;

    public Letters(String s) {
        for (int i = 0; i < s.length(); i++) {
            String s1 = s.substring(i, i + 1);
            Runnable t = () -> runThread(s1);
            threadArrayList.add(new Thread(t, "Thread " + s1));
        }
    }

    private void runThread(String s) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print(s);
                Thread.sleep(Letters.sleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public List<Thread> getThreads() {
        return threadArrayList;
    }



}