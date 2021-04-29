package task1;

import java.util.List;
import java.util.Objects;

public class Employee {

    private final long id;
    private Workplace workplace;

    public Employee(long id, Workplace workplace) {
        this.id = id;
        this.workplace = workplace;
    }

    public long getId() {
        return id;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace, List<Stationery> employeeStationery) {
        this.workplace = workplace;
        workplace.addNewStationery(employeeStationery);
    }

    @Override
    public String toString() {
        return String.format("{id - %d, workplace - %s}", id, workplace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id &&
            Objects.equals(workplace, employee.workplace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workplace);
    }
}
