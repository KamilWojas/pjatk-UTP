/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad3;


import java.util.stream.Collectors;
import java.io.*;
import java.net.URL;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

    Map<String, List<String>> map = reader
            .lines()
            .collect(Collectors.groupingBy(
                    str -> {
                      char[] arr = str.toCharArray();
                      Arrays.sort(arr);
                      return String.valueOf(arr);
                    },
                    () -> new LinkedHashMap<String, List<String>>(),
                    Collectors.toList()
            ));

    int maxSize = map
            .values()
            .stream()
            .map(el -> el.size())
            .max(Integer::compare)
            .get();

    map
            .values()
            .stream()
            .filter(e -> e.size() == maxSize)
            .forEach(e -> System.out.println(e.stream().collect(Collectors.joining(" "))));
  }
}
