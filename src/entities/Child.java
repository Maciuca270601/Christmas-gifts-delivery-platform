package entities;

import fileio.ChildInput;
import visitor.Visitable;
import visitor.Visitor;

import java.util.ArrayList;

public class Child implements Visitable {

    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;
    private Double niceScore;
    private ArrayList<String> giftPreferences;
    private ArrayList<Double> arrayNiceScore;
    private Double avgScore;
    private String category;
    private Double budget;
    private ArrayList<Gift> gifts;

    public Child (ChildInput childData) {
        this.id = childData.getId();
        this.lastName = childData.getLastName();
        this.firstName = childData.getFirstName();
        this.age = childData.getAge();
        this.city = childData.getCity();
        this.niceScore = childData.getNiceScore();
        this.giftPreferences = childData.getGiftsPreferences();
        this.arrayNiceScore = new ArrayList<>();
        this.arrayNiceScore.add(this.niceScore);
        this.avgScore = null;
        this.category = null;
        this.budget = null;
        this.gifts = new ArrayList<>();
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

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftPreferences() {
        return giftPreferences;
    }

    public ArrayList<Double> getArrayNiceScore() {
        return arrayNiceScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public String getCategory() {
        return category;
    }

    public Double getBudget() { return budget;}

    public ArrayList<Gift> getGifts() { return gifts;}

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

//    @Override
//    public String toString() {
//        return  "[ {" + "\n" +
//                "id=" + id + "\n" +
//                ", lastName='" + lastName + "\n" +
//                ", firstName='" + firstName + '\n' +
//                ", age=" + age + '\n' +
//                ", city='" + city + '\n' +
//                ", niceScore=" + niceScore + '\n' +
//                ", giftPreferences=" + giftPreferences + '\n' +
//                ", arrayNiceScore=" + arrayNiceScore + '\n' +
//                ", avgScore=" + avgScore + '\n' +
//                ", category='" + category + '\n' +
//                ", budget=" + budget + '\n' +
//                ", gifts=" + gifts + '\n' +
//                '}';
//    }
}
