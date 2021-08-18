package task1;

public class SynchronizedCounter implements Counter{
    private volatile int count;

    public SynchronizedCounter() {
        count = 0;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public synchronized void increment() {
        count++;
    }

}
