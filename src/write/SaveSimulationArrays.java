package write;

import java.util.ArrayList;

public final class SaveSimulationArrays {

    private final ArrayList<SaveAnnualArray> annualChildren;

    public SaveSimulationArrays() {
        this.annualChildren = new ArrayList<>();
    }

    public ArrayList<SaveAnnualArray> getAnnualChildren() {
        return annualChildren;
    }

    /**
     * This method adds every saveAnnualArray object to this.annualChildren.
     */
    public void addArray(final SaveAnnualArray saveAnnualArray) {
        this.annualChildren.add(saveAnnualArray);
    }
}
