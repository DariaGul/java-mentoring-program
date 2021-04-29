package task2;

import java.util.UUID;
import task1.Stationery;

public abstract class WritingUtensil extends Stationery {

    //письменные принадлежности
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    protected abstract boolean getIndelible();

    public UUID write(String text, PaperProduct placeToWrite) {
       return placeToWrite.saveRecord(text, WritingUtensil.this);
    }
}
