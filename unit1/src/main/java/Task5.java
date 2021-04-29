import java.util.Arrays;

public class Task5 {

    private final static int LENGTH = 11;

    public static void main(String[] args) {

        Byte[][] array = new Byte[LENGTH][LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (i == j || LENGTH - i - 1 == j) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
            }
        }

        Arrays.stream(array).forEach(obj -> {
            Arrays.stream(obj).forEach(cur -> System.out.print(cur + " "));
            System.out.println();
        });
    }
}
