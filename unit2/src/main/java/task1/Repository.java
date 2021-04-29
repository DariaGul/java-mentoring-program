package task1;

import java.util.List;
import java.util.Map;

public class Repository {

    Map<Long, Employee> employees;
    Map<Long, Workplace> workplace;

    public Repository(Map<Long, Employee> employees, Map<Long, Workplace> workplace) {
        this.employees = employees;
        this.workplace = workplace;
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(long id) {
        return employees.get(id);
    }

    public void addNewEmployees(List<Employee> newEmployees) {
        newEmployees.forEach(c -> employees.put(c.getId(), c));
    }

    public void addNewEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public Map<Long, Workplace> getWorkplace() {
        return workplace;
    }

    public void addNewEmployees(Map<Long, Employee> employees) {
        this.employees = employees;
    }
}
