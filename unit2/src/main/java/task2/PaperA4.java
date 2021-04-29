package task2;

public class PaperA4 extends PaperProduct {

    private int thickness;

    public int getThickness() {
        return thickness;
    }

    public PaperA4(int thickness) {
        this.thickness = thickness;
        super.setDurability(5);
        super.setCost(4);
    }
}
