package fileio;

import java.util.ArrayList;

/*
 * The class contains information about the input
 */
public final class Input {

    private final Integer numberOfYears; //iterations for the simulation
    private final Double santaBudget; //initial Santa Budget
    private final ArrayList<ChildInput> initialData; //initial array of children
    private final ArrayList<GiftInput> santaGiftList; //initial array of gifts
    private final ArrayList<YearDataInput> annualChanges; //array of simulation updates

    public Input(final Integer numberOfYears, final Double santaBudget,
                 final ArrayList<ChildInput> initialData, final ArrayList<GiftInput> santaGiftList,
                 final ArrayList<YearDataInput> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.santaGiftList = santaGiftList;
        this.annualChanges = annualChanges;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public ArrayList<ChildInput> getInitialData() {
        return initialData;
    }

    public ArrayList<GiftInput> getSantaGiftList() {
        return santaGiftList;
    }

    public ArrayList<YearDataInput> getAnnualChanges() {
        return annualChanges;
    }
}
