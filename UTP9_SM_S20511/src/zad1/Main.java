/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */

package zad1;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static final List<Product> products;
  public static int id;

  static {
    products = new ArrayList<>();
    id = 0;
  }

  public static void main(String[] args){
    launch();
  }

  public static void launch() {
    try {
      new ThreadA().start();
      new ThreadB().start();
    } catch (FileNotFoundException e) {
      System.out.println("There is no such file!");
    }
  }
}
