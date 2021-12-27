package database;

import entities.Child;
import entities.Gift;
import fileio.ChildInput;
import fileio.GiftInput;
import fileio.YearDataInput;
import java.util.ArrayList;

public class Database {

    private Integer numberOfYears;
    private Double SantaBudget;
    private ArrayList<Child> listOfChildren;
    private ArrayList<Gift> listOfGifts;
    private ArrayList<YearDataInput> annualChanges;

    private static Database instance = null;

    private Database() {
        this.numberOfYears = 0;
        this.SantaBudget = 0d;
        this.listOfChildren = new ArrayList<>();
        this.listOfGifts = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void setNumberOfYears (Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(Double santaBudget) {
        this.SantaBudget = santaBudget;
    }


    public void setListOfChildren (ArrayList<ChildInput> childrenInput) {
        for (ChildInput childInput: childrenInput) {
            Child child = new Child(childInput);
            this.listOfChildren.add(child);
        }
    }

    public void setListOfGifts (ArrayList<GiftInput> giftsInput) {
        for (GiftInput giftInput: giftsInput) {
            Gift gift = new Gift(giftInput);
            this.listOfGifts.add(gift);
        }
    }

    public void setAnnualChanges (ArrayList<YearDataInput> annualChangesInput) {
        this.annualChanges.addAll(annualChangesInput);
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return SantaBudget;
    }

    public ArrayList<Child> getListOfChildren() {
        return listOfChildren;
    }

    public ArrayList<Gift> getListOfGifts() {
        return listOfGifts;
    }

    public ArrayList<YearDataInput> getAnnualChanges() {
        return annualChanges;
    }
}
