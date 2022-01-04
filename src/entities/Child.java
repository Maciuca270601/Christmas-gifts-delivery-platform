package entities;

import fileio.ChildInput;
import visitor.Visitable;
import visitor.Visitor;
import java.util.ArrayList;

public final class Child implements Visitable {

    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private Integer age;
    private ArrayList<String> giftsPreferences;
    private Double averageScore;
    private final ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private final ArrayList<Gift> receivedGifts;
    private final Integer niceScoreBonus;
    private String elf;
    private Double cityScore;

    public Child(final ChildInput childData) {
        this.id = childData.getId();
        this.lastName = childData.getLastName();
        this.firstName = childData.getFirstName();
        this.city = childData.getCity();
        this.age = childData.getAge();
        this.giftsPreferences = new ArrayList<>();
        this.giftsPreferences.addAll(childData.getGiftsPreferences());
        this.averageScore = null;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(childData.getNiceScore());
        this.assignedBudget = null;
        this.receivedGifts = new ArrayList<>();
        this.niceScoreBonus = childData.getNiceScoreBonus();
        this.elf = childData.getElf();
        this.cityScore = null;
    }

    public Child(final Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.city = child.city;
        this.age = child.age;
        this.giftsPreferences = new ArrayList<>();
        this.giftsPreferences.addAll(child.getGiftsPreferences());
        this.averageScore = child.averageScore;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());
        this.assignedBudget = child.assignedBudget;
        this.receivedGifts = new ArrayList<>();
        for (Gift gift: child.receivedGifts) {
            this.receivedGifts.add(new Gift(gift));
        }
        this.niceScoreBonus = child.niceScoreBonus;
        this.elf = child.elf;
        this.cityScore = child.cityScore;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public Integer getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public String getElf() {
        return elf;
    }

    public Double getCityScore() {
        return cityScore;
    }

    public void setCityScore(Double cityScore) {
        this.cityScore = cityScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public void setElf(final String elf) {
        this.elf = elf;
    }

    public void setGiftsPreferences(ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
