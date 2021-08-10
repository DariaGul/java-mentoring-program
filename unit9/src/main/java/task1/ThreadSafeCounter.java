package task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSafeCounter implements Runnable {

    private Counter counter;

    public ThreadSafeCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) {
        Counter synchronizedCounter = new SynchronizedCounter();
        Counter atomicIntCounter = new AtomicIntCounter();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            threadPool.execute(new ThreadSafeCounter(synchronizedCounter));
            threadPool.execute(new ThreadSafeCounter(atomicIntCounter));
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(synchronizedCounter.getCount());
        System.out.println(atomicIntCounter.getCount());
    }

}
