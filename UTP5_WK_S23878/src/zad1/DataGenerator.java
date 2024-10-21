package zad1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {
    public static void main(String[] args) {
        String filePath = "/Users/kamil/Desktop/UTP5_WK_S23878/Towary.txt";
        int numberOfLines = 10000;

        try (FileWriter writer = new FileWriter(filePath)) {
            Random random = new Random();

            for (int i = 0; i < numberOfLines; i++) {
                int id = i + 1;
                int weight = random.nextInt(100) + 1; 

                String line = id + " " + weight + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Wygenerowano plik Towary.txt");
    }
}
