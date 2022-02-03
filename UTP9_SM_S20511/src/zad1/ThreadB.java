package zad1;

public class ThreadB extends Thread {

    private int sum;
    private static boolean exists = true;

    //    CALCULATES SUM OF WEIGHTS
    @Override
    public void run() {
        System.out.println("Thread B started!");
        int calculatedAmount = 0;
        while (exists) {
            synchronized (Main.products) {
                if (Main.id >= Main.products.size()) {
                    try {
                        long millisBefore = System.currentTimeMillis();
                        Main.products.notifyAll();
                        Main.products.wait(10);
                        if (System.currentTimeMillis() - millisBefore > 9) {
                            Thread.currentThread().interrupt();
                        }
                    } catch (InterruptedException e) {
                        exists = false;
                    }
                } else {
                    Product p = Main.products.get(Main.id++);
                    sum += p.weight();
                    calculatedAmount += 1;
                    if (calculatedAmount%100 == 0) {
                        System.out.println("The weight of " + calculatedAmount + " goods was calculated");
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public int sum() {
        return sum;
    }
}

