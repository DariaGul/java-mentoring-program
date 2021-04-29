package task1;

import java.util.HashMap;
import java.util.Map;
import task2.NovicePack;
import task2.Sticker;

public class Main {

    public static void main(String[] args) {

        long id = 1;
        Sticker sticker = new Sticker("Желтый");
        Sticker sticker2 = new Sticker("Желтый");
        Workplace placeForCheck = new Workplace(id);
        placeForCheck.addNewStationery(sticker);
        placeForCheck.addNewStationery(sticker2);

        placeForCheck.addNewStationery(NovicePack.collectNovicePack());
        Employee employee = new Employee(id, placeForCheck);

        Map<Long, Employee> employees = new HashMap<>();
        employees.put(id, employee);
        Map<Long, Workplace> workplace = new HashMap<>();
        workplace.put(id, placeForCheck);
        Repository repository = new Repository(employees, workplace);

        Calculator calculator = new Calculator(repository);
        System.out.println(calculator.getCostOfStationery(id));

        System.out.println(calculator.getCountOfStationery(id));

    }
}
