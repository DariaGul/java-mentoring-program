package task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {

    private Repository repository;

    public Calculator(Repository repository) {
        this.repository = repository;
    }

    public Map<String, Integer> getCountOfStationery(long id) {

        Employee employee = repository.getEmployee(id);
        List<Stationery> stationery = employee.getWorkplace().getStationery();

        Map<String, Integer> mapStationery = new HashMap<>();
        if (stationery.isEmpty()) {
            return mapStationery;
        }
        stationery.forEach(c -> {
                               String className = c.getClass().getSimpleName();
                               Integer result = mapStationery.putIfAbsent(className, 1);
                               if (result != null) {
                                   mapStationery.computeIfPresent(className, (k, v) -> ++v);
                               }
                           }
        );

        return mapStationery;
    }

    public double getCostOfStationery(long id) {

        return repository.getEmployee(id)
            .getWorkplace()
            .getStationery()
            .stream()
            .mapToDouble(Stationery::getCost)
            .sum();
    }


}