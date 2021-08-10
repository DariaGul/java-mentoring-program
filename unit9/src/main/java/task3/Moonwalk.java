package task3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Moonwalk implements Runnable {

    private final String name;
    private final ReentrantLock locker;
    private final Condition condition;

    public Moonwalk(String name, ReentrantLock locker, Condition condition) {
        this.name = name;
        this.locker = locker;
        this.condition = condition;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            while (true) {
                condition.signal();
                condition.await();
                System.out.println(name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock locker = new ReentrantLock();
        Condition condition = locker.newCondition();

        CompletableFuture.allOf(
            CompletableFuture.runAsync(new Moonwalk("left", locker, condition)),
            CompletableFuture.runAsync(new Moonwalk("right", locker, condition))
        ).join();
    }
}
