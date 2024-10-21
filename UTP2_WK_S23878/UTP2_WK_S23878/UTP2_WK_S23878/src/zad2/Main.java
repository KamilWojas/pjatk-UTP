/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        /*<--
         *  definicja operacji w postaci lambda-wyrażeń:
         *  - flines - zwraca listę wierszy z pliku tekstowego
         *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
         *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
         *  - sum - zwraca sumę elmentów listy liczb całkowitych
         */

        Function<String, List<String>> flines = (filePath) -> {
            List<String> linesFromFile = new ArrayList<>();
            FileReader fr;
            BufferedReader br;
            try {
                fr = new FileReader(filePath);
                br = new BufferedReader(fr);
                String lineFromFile;
                while ((lineFromFile = br.readLine()) != null) linesFromFile.add(lineFromFile);
                return linesFromFile;
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<String>();
            }
        };

        Function<List<String>, String> join = (linesFromFile) -> String.join("", linesFromFile);

        Function<String, List<Integer>> collectInts = (lineFromFile) -> {
            List<Integer> onlyNumbers = new ArrayList<>();
            Matcher matcher = Pattern.compile("\\d+").matcher(lineFromFile);
            while (matcher.find()) onlyNumbers.add(Integer.parseInt(matcher.group()));
            return onlyNumbers;
        };

        Function<List<Integer>, Integer> sum = (ints) -> {
            int intsSum = 0;
            for (Integer i : ints) intsSum += i;
            return intsSum;
        };


        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

    }
}
