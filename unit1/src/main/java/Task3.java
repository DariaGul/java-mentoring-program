import java.util.Random;

public class Task3 {

    public static void main(String[] args) {

        int h = (int) (Math.random() * 10);
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(100) + (int) Math.pow(a, 2);
        for (int x = a; x < b; x += h) {
            double y = Math.tan(2 * x) - 3;
            System.out.printf("x = %s y = %s%n", x, y);
        }
    }
}
