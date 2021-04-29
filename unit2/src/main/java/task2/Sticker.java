package task2;

public class Sticker extends PaperProduct {

    private String color;

    public String getColor() {
        return color;
    }

    public Sticker(String color) {
        super.setDurability(1);
        super.setCost(5);
        this.color = color;
    }
}
