package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Workplace {

    private final Long id;
    private List<Stationery> stationery;

    public Workplace(long id) {
        this.id = id;
        stationery = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<Stationery> getStationery() {
        return stationery;
    }

    public void addNewStationery(List<Stationery> stationery) {
        this.stationery.addAll(stationery);
    }

    public void addNewStationery(Stationery stationery) {
        this.stationery.add(stationery);
    }

    @Override
    public String toString() {
        return String.format("{id - %d, stationery on table - %s}", id, stationery.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workplace workplace = (Workplace) o;
        return Objects.equals(id, workplace.id) &&
            Objects.equals(stationery, workplace.stationery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationery);
    }
}
