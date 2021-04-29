package task5;

@FunctionalInterface
interface ThirdTask<T extends Number> {

    T sum(T a, T b);
}