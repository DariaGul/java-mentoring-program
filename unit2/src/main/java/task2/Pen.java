package task2;

//ручка
public class Pen extends WritingUtensil {

    private String type;

    public Pen(String type){
        super.setCost(25);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected boolean getIndelible() {
        return true;
    }
}
