package task2;

//ластик
public class Eraser extends Corrector {

    private int softness;

    public Eraser(int softness){
        super.setCost(30);
        this.softness = softness;
    }

    public int getSoftness() {
        return softness;
    }

    public void setSoftness(int softness) {
        this.softness = softness;
    }

    @Override
    protected boolean getErasingEverything() {
        return false;
    }

}
