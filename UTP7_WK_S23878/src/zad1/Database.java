package zad1;

import java.sql.*;
import javax.swing.*;

class Database {


    private final String url;
    private final TravelData travelData;

    Database(String url, TravelData travelData) {
        this.travelData = travelData;
        this.url = url;
    }

    void create() {
        try (
                Connection conn = DriverManager.getConnection(url)
        ) {
            if (conn != null) {
                conn.getMetaData();
            }

            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS traveldata (data TEXT NOT NULL);");

            PreparedStatement preparedStatement
                    = conn.prepareStatement("insert into traveldata (data) values (?)");

            for (Entry e : travelData.getData()) {
                preparedStatement.setString(1, e.toString());
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    void showGui() {
        SwingUtilities.invokeLater(() -> new GUI(travelData));
    }

    public static String[] getLocales() {
        return new String[] {
                "PL",
                "EN",
        };
    }
}
