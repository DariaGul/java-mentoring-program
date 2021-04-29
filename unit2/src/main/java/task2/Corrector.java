package task2;

import task1.Stationery;

public abstract class Corrector extends Stationery {

    private int durability = 10;

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    protected abstract boolean getErasingEverything();
}

