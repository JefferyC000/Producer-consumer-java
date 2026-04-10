import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBuffer {

    private final Queue<String> buffer = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    private boolean finished = false;

    public void produce(String item) {
        lock.lock();
        try {
            buffer.add(item);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String consume() {
        lock.lock();
        try {
            while (buffer.isEmpty() && !finished) {
                notEmpty.await();
            }

            if (buffer.isEmpty() && finished) {
                return null;
            }

            return buffer.poll();
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void setFinished() {
        lock.lock();
        try {
            finished = true;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
}