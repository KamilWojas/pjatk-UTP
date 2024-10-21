package zad2;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Futil {

    private static Charset DEST_FILE_ENCODING = StandardCharsets.UTF_8;
    private static Charset SRC_FILE_ENCODING = Charset.forName("Cp1250");

    public static void processDir(String dirName, String resultFileName) {
        File resultFile = new File(resultFileName);

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(resultFileName)), DEST_FILE_ENCODING))) {
            Files.walk(Paths.get(dirName)).filter(Files::isRegularFile).forEach(path -> {
                try {
                    Files.lines(path, SRC_FILE_ENCODING)
                            .forEach(line -> {
                                try {
                                    out.write(line + "\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}