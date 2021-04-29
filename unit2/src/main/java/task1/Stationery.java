package task1;

import java.util.Objects;

//канцелярские товары
public abstract class Stationery {

    private double cost = 50;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("{name - %s, cost - %s}", getClass().getSimpleName(), getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stationery that = (Stationery) o;
        return Double.compare(that.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }
}
