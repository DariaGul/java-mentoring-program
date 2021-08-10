package task3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class NowLittleAnimalsCanWalk implements Runnable {

    private final String name;
    private final Semaphore semaphore;
    private final CountDownLatch countDownLatch;

    public NowLittleAnimalsCanWalk(String name, Semaphore semaphore, CountDownLatch countDownLatch) {
        this.name = name;
        this.semaphore = semaphore;
        this.countDownLatch = countDownLatch;

    }

    @Override
    public void run() {
        try {
            countDownLatch.countDown();
            countDownLatch.await();
            while (true) {
                semaphore.acquire();
                System.out.println(name);
                semaphore.release();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        CompletableFuture.allOf(
            CompletableFuture.runAsync(new NowLittleAnimalsCanWalk("left", semaphore, countDownLatch)),
            CompletableFuture.runAsync(new NowLittleAnimalsCanWalk("right", semaphore, countDownLatch))
        ).join();
    }

}



