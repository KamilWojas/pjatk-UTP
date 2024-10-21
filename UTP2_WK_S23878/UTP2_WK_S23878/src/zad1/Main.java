/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad1;

/*<-- niezbędne importy */
import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<String> dest = Arrays.asList(
            "bleble bleble 2000",
            "WAW HAV 1200",
            "xxx yyy 789",
            "WAW DPS 2000",
            "WAW HKT 1000"
    );


      double ratePLNvsEUR = 4.30;
      List <String> result = dest.stream()
              .filter(item -> item.split(" ")[0].equals("WAW"))
              .map(item -> "to " + item.split(" ")[0] + " - price in PLN: " + Integer.parseInt(item.split(" ")[2])*ratePLNvsEUR)
              .collect(Collectors.toList());
      for (String r: result) System.out.println(r);
  }
}
