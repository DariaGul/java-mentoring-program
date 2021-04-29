package task5;

public class Main {

    public static void main(String[] args) {
        FirstTask firstTask = (value) -> value % 13 == 0;
        System.out.println(firstTask.mod(26));
        System.out.println(firstTask.mod(44));
        System.out.println();

        SecondTask secondTask = (a, b, c) -> Math.pow(b, 2) - 4 * a * c;
        System.out.println(secondTask.getDiscriminant(6, 4, 1));
        System.out.println(secondTask.getDiscriminant(3, 18, 27));
        System.out.println(secondTask.getDiscriminant(2, 8, 3));

        System.out.println();
        ThirdTask<Integer> thirdTaskInt = Integer::sum;
        System.out.println(thirdTaskInt.sum(2, 4));
        ThirdTask<Double> thirdTaskDouble = Double::sum;
        System.out.println(thirdTaskDouble.sum(2.3, 4.8));
        ThirdTask<Float> thirdTaskFloat = Float::sum;
        System.out.println(thirdTaskFloat.sum(9.33f, 2.82f));
    }
}
