package src.com.supinfo.philosophers;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Fork {

    private Philosopher philosopher;

    public void leaveFork() {
        philosopher = null;
    }

    public void takeFork(Philosopher philosopher) {
        this.philosopher = philosopher;
    }

    public boolean isFree() {
        return philosopher == null;
    }
}
