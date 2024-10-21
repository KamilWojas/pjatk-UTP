package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadA implements Runnable {
    private String filePath;

    public ThreadA(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            AtomicInteger objectCount = new AtomicInteger(0);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length == 2) {
                    int id = Integer.parseInt(data[0]);
                    int weight = Integer.parseInt(data[1]);

                    Towar towar = new Towar(id, weight);
                    objectCount.incrementAndGet();

                    if (objectCount.get() % 200 == 0) {
                        System.out.println("Utworzono " + objectCount.get() + " obiektów");
                    }
                }
            }

            System.out.println("Wątek A zakończył pracę");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}