package write;

import entities.Child;

import java.util.ArrayList;

public class SaveSimulationArrays {

    public ArrayList<SaveAnnualArray> annualChildren;

    public SaveSimulationArrays () {
        this.annualChildren = new ArrayList<>();
    }

    public ArrayList<SaveAnnualArray> getAnnualChildren() {
        return annualChildren;
    }

    public void addArray(SaveAnnualArray saveAnnualArray) {
        this.annualChildren.add(saveAnnualArray);
    }
}
