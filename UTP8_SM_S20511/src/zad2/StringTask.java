package zad2;

public class StringTask implements Runnable{
    private volatile State state;
    private Thread thread;
    private String result;
    private String taskName;
    private int number;

    public StringTask (String taskName, int number) {
        this.taskName = taskName;
        this.number = number;
        this.state = State.CREATED;
        this.result = "";
    }

    public String getResult () {
        return result;
    }

    public State getState () {
        return state;
    }

    public void start () {
        if (thread == null) {
            thread = new Thread(this, taskName);
            thread.start();
        }
    }

    public void abort () {
        state = State.ABORTED;
        thread.interrupt();
    }

    public boolean isDone () {
        return state == State.READY || state == State.ABORTED;
    }


    @Override
    public void run () {
        state = State.RUNNING;
        for (int i = 0; i < number && state == State.RUNNING && !thread.isInterrupted(); i++) {
            result += taskName;
        }
        if (state != State.ABORTED) {
            state = State.READY;
        }
    }
}
