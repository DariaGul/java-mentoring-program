package task2;

//карандаш
public class Pencil extends WritingUtensil {

    private String rigidity;

    public Pencil(String rigidity) {
        super.setCost(15);
        this.rigidity = rigidity;
    }

    public String getRigidity() {
        return rigidity;
    }

    public void setRigidity(String rigidity) {
        this.rigidity = rigidity;
    }

    @Override
    protected boolean getIndelible() {
        return false;
    }

}
