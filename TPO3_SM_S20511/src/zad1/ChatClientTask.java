/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */

package zad1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ChatClientTask extends FutureTask<ChatClient> {

    private ChatClientTask(Callable<ChatClient> callable) {
        super(callable);
    }

    static ChatClientTask create(ChatClient c, List<String> msgs, int wait) {
        return new ChatClientTask(() -> {
            try {
                c.login();
                if (wait != 0)
                    Thread.sleep(wait);
                for (String msg : msgs) {
                    c.send(msg);
                    if (wait != 0) Thread.sleep(wait);
                }
                c.logout();
                if (wait != 0)
                    Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return c;
        });
    }

    ChatClient getClient() {
        try {
            return this.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}