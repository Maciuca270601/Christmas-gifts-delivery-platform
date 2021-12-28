package database;

import entities.Child;
import entities.Gift;
import fileio.ChildInput;
import fileio.GiftInput;
import fileio.YearDataInput;
import utils.Utils;

import java.util.ArrayList;

public class Database {

    private Integer numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> listOfChildren;
    private ArrayList<Gift> listOfGifts;
    private ArrayList<Gift> boardGames;
    private ArrayList<Gift> books;
    private ArrayList<Gift> clothes;
    private ArrayList<Gift> sweets;
    private ArrayList<Gift> technology;
    private ArrayList<Gift> toys;
    private ArrayList<YearDataInput> annualChanges;

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

    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void setNumberOfYears (Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
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

    public void sortListOfGifts () {
        this.boardGames = Utils.sortGiftList(boardGames);
        this.books = Utils.sortGiftList(books);
        this.clothes = Utils.sortGiftList(clothes);
        this.sweets = Utils.sortGiftList(sweets);
        this.technology = Utils.sortGiftList(technology);
        this.toys = Utils.sortGiftList(toys);
    }

    public void setAnnualChanges (ArrayList<YearDataInput> annualChangesInput) {
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
}
