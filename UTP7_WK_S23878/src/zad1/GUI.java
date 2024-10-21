package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUI {
    JFrame f;
    TravelData travelData;
    JScrollPane sp;

    public GUI(TravelData travelData) {
        this.travelData = travelData;
        this.f = new JFrame();
        f.setLayout(new BorderLayout());
        sp = new JScrollPane();
        f.add(sp, BorderLayout.CENTER);
        this.table();
    }


    void table() {
        String locales[] = Database.getLocales();
        JComboBox<String> dropdown = new JComboBox(locales);
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readdPanel(dropdown.getSelectedItem().toString());
            }
        });


        JPanel ddp = new JPanel(new BorderLayout());
        ddp.add(new JLabel("Locale  "), BorderLayout.LINE_START);
        ddp.add(dropdown, BorderLayout.CENTER);

        f.add(ddp, BorderLayout.PAGE_START);
        readdPanel(dropdown.getSelectedItem().toString());
        f.setSize(500, 200);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void readdPanel(String loc) {
        this.f.remove(this.sp);
        this.sp = generatePanel(loc);
        this.f.add(sp, BorderLayout.CENTER);
        f.setVisible(true);
    }

    JTable generateTable(String loc) {
        ResourceBundle bundle = ResourceBundle.getBundle("dictionary", Locale.forLanguageTag(loc.replace("_", "-")));
        return new JTable(
                travelData.getDataAsStringArray(loc),
                new String[]{
                        bundle.getString("locale"),
                        bundle.getString("country"),
                        bundle.getString("departure"),
                        bundle.getString("return"),
                        bundle.getString("place"),
                        bundle.getString("price"),
                        bundle.getString("currency")}
        );
    }

    JScrollPane generatePanel(String loc) {
        JTable jt = generateTable(loc);
        jt.setBounds(30, 40, 500, 300);
        jt.setFillsViewportHeight(true);
        return new JScrollPane(jt);
    }

}
