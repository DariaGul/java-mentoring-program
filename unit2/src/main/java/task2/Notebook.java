package task2;

//блокнот
public class Notebook extends PaperProduct {

    private boolean hasRingMechanism;

    public int getHasRingMechanism() {
        return super.getDurability();
    }

    public Notebook(boolean hasRingMechanism) {
        this.hasRingMechanism = hasRingMechanism;
        super.setDurability(100);
        super.setCost(60);
    }
}
