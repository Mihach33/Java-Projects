/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */

package zad1;


import javax.swing.*;


public class Main {
  public static void main(String[] args) {

    Service s = new Service("Germany");
    String weatherJson = s.getWeather("Berlin");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...
    // część uruchamiająca GUI
    Gui();

  }
  public static void Gui () {
    JFrame jFrame = new GuiManager("TPO2");
    jFrame.setVisible(true);
  }
}