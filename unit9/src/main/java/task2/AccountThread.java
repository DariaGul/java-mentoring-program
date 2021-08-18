package task2;

import java.util.concurrent.locks.ReentrantLock;

public class AccountThread implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;
    private ReentrantLock locker;

    public AccountThread(Account accountFrom, Account accountTo, int money, ReentrantLock locker) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
        this.locker = locker;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            locker.lock();
            System.out.printf("BEFORE: accountFrom: - %d, accountTo: - %d, ",
                              accountFrom.getCacheBalance(),
                              accountTo.getCacheBalance());
            if (accountFrom.takeOffMoney(money)) {
                accountTo.addMoney(money);
                System.out.printf("AFTER: accountFrom: - %d, accountTo: - %d, money - %s%n",
                                  accountFrom.getCacheBalance(),
                                  accountTo.getCacheBalance(), money);
            } else {
                System.out.printf("money - %d%n", money);
            }
            locker.unlock();
        }
    }
}