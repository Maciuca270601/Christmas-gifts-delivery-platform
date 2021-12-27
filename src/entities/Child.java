package entities;

import fileio.ChildInput;

import java.util.ArrayList;

public class Child {

    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;
    private ArrayList<Double> niceScore;
    private Double avgScore;
    private String category;
    private ArrayList<String> giftPreferences;

    public Child (ChildInput childData) {
        this.id = childData.getId();
        this.lastName = childData.getLastName();
        this.firstName = childData.getFirstName();
        this.age = childData.getAge();
        this.city = childData.getCity();
        this.niceScore = new ArrayList<>();
        this.niceScore.add(childData.getNiceScore());
        this.avgScore = childData.getNiceScore();
        this.category = null;
        this.giftPreferences = childData.getGiftsPreferences();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Double> getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(ArrayList<Double> niceScore) {
        this.niceScore = niceScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public ArrayList<String> getGiftPreferences() {
        return giftPreferences;
    }

    public void setGiftPreferences(ArrayList<String> giftPreferences) {
        this.giftPreferences = giftPreferences;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
