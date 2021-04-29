package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3)
@Measurement(iterations = 2)
@Fork(value = 2)
public class PerformanceTest {

    @Param({"50000", "500000", "1000000"})
    private int arraySize;
    private int middleElement;
    private int lastElement;

    private List<String> arrayList = new ArrayList<>();
    private List<String> linkedList = new LinkedList<>();

    @Setup(Level.Invocation)
    public void load() {
        middleElement = arraySize / 2;
        lastElement = arraySize - 1;
        String[] dataArray = new String[arraySize];
        for (int i = 0; i < arraySize - 1; i++) {
            dataArray[i] = "example_element";
        }
        dataArray[arraySize - 1] = "for_delete";
        arrayList = new ArrayList<>(Arrays.asList(dataArray));
        linkedList = new LinkedList<>(Arrays.asList(dataArray));
    }

    @Benchmark
    public String getArray() {
        return arrayList.get(middleElement);
    }

    @Benchmark
    public String getLinked() {
        return linkedList.get(middleElement);
    }

    @Benchmark
    public Boolean containsArray() {
        return arrayList.contains("example_element");
    }

    @Benchmark
    public Boolean containsLinked() {
        return linkedList.contains("example_element");
    }

    @Benchmark
    public boolean addInEndArray() {
        return arrayList.add("element1");
    }

    @Benchmark
    public boolean addInEndLinked() {
        return linkedList.add("element1");
    }

    @Benchmark
    public void addInMiddleArray() {
        arrayList.add(middleElement, "middleElement");
    }

    @Benchmark
    public void addInMiddleLinked() {
        linkedList.add(middleElement, "middleElement");
    }

    @Benchmark
    public Boolean removeWithObjArray() {
        return arrayList.remove("for_delete");
    }

    @Benchmark
    public Boolean removeWithObjLined() {
        return linkedList.remove("for_delete");
    }

    @Benchmark
    public String removeWithIndexArray() {
        return arrayList.remove(middleElement);
    }

    @Benchmark
    public String removeWithIndexLinked() {
        return linkedList.remove(middleElement);
    }

}
