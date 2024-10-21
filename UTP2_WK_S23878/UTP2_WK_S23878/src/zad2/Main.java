/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            Consumer<List<String>> flines = (file) -> {
                List<String> result;
                String fileName;
                try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
                    result = lines.collect(Collectors.toList());
                }
                return result;
            };
            /*
             * - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
             */
            Consumer<String> join = (listOfStrings) -> {
                String result = "";
                for(String s : listOfStrings) {
                    result += s;
                }
                return result;
            };
            /*
             * - collectInts - zwraca listę liczb całkowitych zawartych w napisie
             */
            Consumer<List<Integer>> collectInts = (text) -> {
                List<Integer> result;
                for(char c : text.toCharArray()) {
                    if(Character.isDigit(c))
                        result.add(Integer.parseInt(String.valueOf(ch)));
                }
                return result;
            };
            /*
             * - sum - zwraca sumę elmentów listy liczb całkowitych
             */
            Consumer<Integer> sum = (integersList) -> {
                int result = 0;
                for(Integer i : integersList) {
                    result += i;
                }
                return result;
            }
        };


        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        Function join;
        String text = fileConv.convertBy(flines, join);
        Object collectInts;
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Object sum;
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
