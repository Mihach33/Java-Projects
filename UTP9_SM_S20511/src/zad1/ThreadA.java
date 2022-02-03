package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThreadA extends Thread {

    private BufferedReader reader;

    public ThreadA() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("./files/Products.txt"));
    }

    //    READS FILE
    @Override
    public void run() {
        System.out.println("Thread A started!");
        String line;
        String[] data;
        try {
            while ((line = reader.readLine()) != null) {
                synchronized (Main.products) {
                    data = line.split("\\s+");
                    Main.products.add(new Product(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
                    if (Main.products.size()%200 == 0) {
                        System.out.println(Main.products.size() + " objects were created");
                    }
                    Main.products.notifyAll();
                }
            }
        } catch (Exception ignore) { }
    }
}

