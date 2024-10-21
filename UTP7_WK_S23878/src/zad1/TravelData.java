package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    List<Entry> data;

    public TravelData(File dataDir) {
        data = new ArrayList<>();
        SimpleDateFormat toSdf = new SimpleDateFormat("yyyy-MM-dd");
        Arrays.stream(Objects.requireNonNull(dataDir.listFiles())).forEach(file -> {
            try {
                Files.lines(Paths.get(file.getPath())).forEach(line -> {
                    String[] lineData = line.split("\t");
                    int i = 0;
                    Entry entry;
                    Locale locale = Locale.forLanguageTag(lineData[i++].replace("_", "-"));
                    NumberFormat toNf = NumberFormat.getInstance(locale);
                    ResourceBundle bundle = ResourceBundle.getBundle("dictionary", locale);

                    try {
                        entry = new Entry(
                                locale,
                                lineData[i++],
                                toSdf.parse(lineData[i++]),
                                toSdf.parse(lineData[i++]),
                                getWord(locale, lineData[i++]),
                                toNf.parse(lineData[i++]).doubleValue(),
                                lineData[i]
                        );

                        data.add(entry);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    List<String> getOffersDescriptionsList(String loc, String dateFormat) {
        ArrayList<String> printableList = new ArrayList<>();

        Locale toLoc = Locale.forLanguageTag(loc.replace("_", "-"));
        NumberFormat toNf = NumberFormat.getInstance(toLoc);
        SimpleDateFormat toSdf = new SimpleDateFormat(dateFormat);

        data.forEach(d -> {
            StringBuilder strB = new StringBuilder();

            String country = getCountry(d.loc, toLoc, d.country);
            strB.append(country).append(" ");
            strB.append(toSdf.format(d.dateFrom)).append(" ");
            strB.append(toSdf.format(d.dateTo)).append(" ");
            strB.append(getWord(toLoc, d.location)).append(" ");
            strB.append(toNf.format(d.price)).append(" ");
            strB.append(d.currency);

            printableList.add(strB.toString());
        });

        return printableList;
    }

    private String getCountry(Locale fromLoc, Locale loc, String country) {
        for (Locale l : Locale.getAvailableLocales())
            if (l.getDisplayCountry(fromLoc).equals(country))
                return l.getDisplayCountry(loc);
        return "";
    }

    private String getWord(Locale loc, String word) {
        ResourceBundle bundle = ResourceBundle.getBundle("dictionary", loc);
        String[] tags = {"sea", "mountains", "lake"};
        for (String tag : tags)
            if (word.toLowerCase().equals(bundle.getString(tag).toLowerCase())) {
                return tag;
            }
        return bundle.getString(word);
    }

    List<Entry> getData() {
        return data;
    }

    String[][] getDataAsStringArray(String loc) {
        String[][] resArray = new String[data.size()][7];

        Locale toLoc = Locale.forLanguageTag(loc.replace("_", "-"));
        NumberFormat toNf = NumberFormat.getInstance(toLoc);
        SimpleDateFormat toSdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < data.size(); i++) {
            resArray[i][0] = loc;
            resArray[i][1] = getCountry(data.get(i).loc, toLoc, data.get(i).country);
            resArray[i][2] = toSdf.format(data.get(i).dateFrom);
            resArray[i][3] = toSdf.format(data.get(i).dateTo);
            resArray[i][4] = getWord(toLoc, data.get(i).location);
            resArray[i][5] = toNf.format(data.get(i).price);
            resArray[i][6] = data.get(i).currency;
        }

        return resArray;
    }
}

class Entry {
    Locale loc;
    String country;
    Date dateFrom; //YYYY-MM-DD
    Date dateTo; //YYYY-MM-DD
    String location;
    Double price;
    String currency;

    Entry(Locale loc, String country, Date dateFrom, Date dateTo, String location, Double price, String currency) {
        this.loc = loc;
        this.country = country;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.location = location;
        this.price = price;
        this.currency = currency;
    }

}