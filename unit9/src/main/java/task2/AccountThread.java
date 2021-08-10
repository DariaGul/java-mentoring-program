package task2;

public class AccountThread implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    public AccountThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            synchronized (this) {
                if(accountFrom.takeOffMoney(money)){
                    accountTo.addMoney(money);
                }
                System.out.printf("accountFrom: - %d, accountTo: - %d, money - %s%n",
                                  accountFrom.getCacheBalance(),
                                  accountTo.getCacheBalance(), money);
            }
        }
    }
}