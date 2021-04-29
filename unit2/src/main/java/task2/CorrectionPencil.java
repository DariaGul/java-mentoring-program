package task2;

//Корректор для текста
public class CorrectionPencil extends Corrector {

    private int thickness;

    public CorrectionPencil(int thickness){
        super.setCost(50);
        this.thickness = thickness;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    protected boolean getErasingEverything() {
        return true;
    }

}
