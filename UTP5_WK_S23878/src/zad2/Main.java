/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad2;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Main {

  public static void main(String[] args) {
    Font font = new Font("Arial", Font.PLAIN, 12);
    UIManager.put("Button.font", font);
    UIManager.put("Label.font", font);
    UIManager.put("TextField.font", font);

    JFrame frame = new JFrame("MyApp");
    JPanel panel = new JPanel();

    JButton button = new JButton("Click me");

    panel.add(button);

    frame.getContentPane().add(panel);

    frame.pack();
    frame.setVisible(true);
  }
}
