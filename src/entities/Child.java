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
    private final ArrayList<String> giftsPreferences;
    private Double averageScore;
    private final ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private final ArrayList<Gift> receivedGifts;

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

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
