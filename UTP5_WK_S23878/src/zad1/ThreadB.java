package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadB implements Runnable {
    private String filePath;

    public ThreadB(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            AtomicInteger weightSum = new AtomicInteger(0);
            AtomicInteger objectCount = new AtomicInteger(0);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length == 2) {
                    int weight = Integer.parseInt(data[1]);

                    objectCount.incrementAndGet();
                    weightSum.addAndGet(weight);

                    if (objectCount.get() % 100 == 0) {
                        System.out.println("Policzono wagę " + objectCount.get() + " towarów");
                    }
                }
            }

            System.out.println("Sumaryczna waga wszystkich towarów: " + weightSum.get());
            System.out.println("Wątek B zakończył pracę");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
