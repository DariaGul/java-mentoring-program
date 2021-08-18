package task1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntCounter implements Counter {

    private final AtomicInteger count;

    public AtomicIntCounter() {
        count = new AtomicInteger();
    }

    @Override
    public int getCount() {
        return count.get();
    }

    @Override
    public void increment() {
        count.getAndIncrement();
    }
}
