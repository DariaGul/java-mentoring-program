import java.util.Arrays;

public class Task4 {

    private final static int LENGTH = 50;

    public static void main(String[] args) {

        int[] array = new int [LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        int maxSum = 0;
        for (int i = 1; i < array.length / 2; i++) {
            int currentSum = array[i] + array[array.length - i - 1];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }

        }
        System.out.println(maxSum);
    }
}
