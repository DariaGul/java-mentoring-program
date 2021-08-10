package task2;

public class AccountThreadDeadLock implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    public AccountThreadDeadLock(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            synchronized (accountFrom) {

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (accountTo) {
                    if (accountFrom.takeOffMoney(money)) {
                        accountTo.addMoney(money);
                    }
                    System.out.printf("accountFrom: - %d, accountTo: - %d, money - %s%n",
                                      accountFrom.getCacheBalance(),
                                      accountTo.getCacheBalance(), money);
                }
            }
        }
    }
}
