package zad1;

public class Letters implements Runnable{
    Thread [] threads;
    boolean runs = true;
//    boolean toName = true;
    char c ;
//    Runnable [] runnables;

    public Letters(String letters) {
        threads = new Thread[letters.length()];
//        runnables = new Runnable[letters.length()];

        for (int i = 0; i < threads.length; i++) {
            int index = i;
            threads[i] = new Thread(new LetterForRun(letters.charAt(i)), "Thread " + letters.charAt(i));

            /*runnables[i] = new Thread(() ->{
                while(runs) {
                    try {
                        System.out.print(c);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Thread.currentThread().setName("Thread " + letters.charAt(index));
                }
            });*/
        }
    }

    public Thread[] getThreads() {
        return threads;
    }

    public void stop(){
        this.runs = false;
    }

    public void forStart(){
        this.runs = true;
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    @Override
    public void run() {
        while(runs) {
            try {
                System.out.print(c);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class LetterForRun implements Runnable{
        char c;

        public LetterForRun(char c) {
            this.c = c;
        }

        @Override
        public void run() {
            while (runs){
                try {
                    System.out.print(c);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
