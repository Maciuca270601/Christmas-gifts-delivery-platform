package fileio;

import java.util.ArrayList;

/*
 * The class contains information about the input
 */
public final class Input {

    private Integer numberOfYears; //iterations for the simulation
    private Double santaBudget; //initial Santa Budget
    private ArrayList<ChildInput> initialData; //initial array of children
    private ArrayList<GiftInput> santaGiftList; //initial array of gifts
    private ArrayList<YearDataInput> annualChanges; //array of simulation updates

    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0d;
        this.initialData = null;
        this.santaGiftList = null;
        this.annualChanges = null;
    }

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
