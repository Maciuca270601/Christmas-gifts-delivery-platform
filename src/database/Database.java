package database;

import entities.Child;
import entities.Gift;
import fileio.ChildInput;
import fileio.GiftInput;
import fileio.YearDataInput;
import strategy.SortStrategy;
import utils.Utils;
import java.util.ArrayList;

public final class Database {

    private Integer numberOfYears;
    private Double santaBudget;
    private final ArrayList<Child> listOfChildren;
    private final ArrayList<Gift> listOfGifts;
    private final ArrayList<Gift> boardGames;
    private final ArrayList<Gift> books;
    private final ArrayList<Gift> clothes;
    private final ArrayList<Gift> sweets;
    private final ArrayList<Gift> technology;
    private final ArrayList<Gift> toys;
    private final ArrayList<YearDataInput> annualChanges;

    private static Database instance = null;

    private Database() {
        this.numberOfYears = 0;
        this.santaBudget = 0d;
        this.listOfChildren = new ArrayList<>();
        this.listOfGifts = new ArrayList<>();
        this.boardGames = new ArrayList<>();
        this.books = new ArrayList<>();
        this.clothes = new ArrayList<>();
        this.sweets = new ArrayList<>();
        this.technology = new ArrayList<>();
        this.toys = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
    }

    /**
     * This method is used to initialize the Database using a EagerSingleton Pattern.
     * @return instance
     *      -> returns a Database object.
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * This method is used to populate the array of children from the database.
     * @param childrenInput
     *      -> receives an array of children from the json file.
     */
    public void setListOfChildren(final ArrayList<ChildInput> childrenInput) {
        for (ChildInput childInput: childrenInput) {
            Child child = new Child(childInput);
            this.listOfChildren.add(child);
        }
    }

    /**
     * This method is used to populate both the general array of gifts and the specific ones too.
     * @param giftsInput
     *      -> receives an array of gifts from the json file.
     */
    public void setListOfGifts(final ArrayList<GiftInput> giftsInput) {
        for (GiftInput giftInput: giftsInput) {
            Gift gift = new Gift(giftInput);

            if (gift.getCategory().equals("Board Games")) {
                boardGames.add(gift);
            }
            if (gift.getCategory().equals("Books")) {
                books.add(gift);
            }
            if (gift.getCategory().equals("Clothes")) {
                clothes.add(gift);
            }
            if (gift.getCategory().equals("Sweets")) {
                sweets.add(gift);
            }
            if (gift.getCategory().equals("Technology")) {
                technology.add(gift);
            }
            if (gift.getCategory().equals("Toys")) {
                toys.add(gift);
            }
            this.listOfGifts.add(gift);
        }
    }

    /**
     * This method sorts the arrays of gifts from the database.
     */
    public void sortListOfGifts() {
        Utils.sortGiftList(boardGames);
        Utils.sortGiftList(books);
        Utils.sortGiftList(clothes);
        Utils.sortGiftList(sweets);
        Utils.sortGiftList(technology);
        Utils.sortGiftList(toys);
    }

    public void sortListOfChildren(SortStrategy sortStrategy) {
        sortStrategy.sort();
    }

    /**
     * This method resets the data from the database between different tests.
     */
    public void resetDatabase() {
        this.numberOfYears = 0;
        this.santaBudget = 0d;
        this.listOfChildren.clear();
        this.listOfGifts.clear();
        this.boardGames.clear();
        this.books.clear();
        this.clothes.clear();
        this.sweets.clear();
        this.technology.clear();
        this.toys.clear();
        this.annualChanges.clear();
    }

    /**
     * This method is used to populate the annualChanges array from the database.
     * @param annualChangesInput
     *      -> receives an array of changes for each year from the json file.
     */
    public void setAnnualChanges(final ArrayList<YearDataInput> annualChangesInput) {
        this.annualChanges.addAll(annualChangesInput);
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
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

    public ArrayList<Gift> getBoardGames() {
        return boardGames;
    }

    public ArrayList<Gift> getBooks() {
        return books;
    }

    public ArrayList<Gift> getClothes() {
        return clothes;
    }

    public ArrayList<Gift> getSweets() {
        return sweets;
    }

    public ArrayList<Gift> getTechnology() {
        return technology;
    }

    public ArrayList<Gift> getToys() {
        return toys;
    }
}
