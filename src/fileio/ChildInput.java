package fileio;

import java.util.ArrayList;

public class ChildInput {

    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;

    public ChildInput(Integer id, String lastName, String firstName, Integer age,
                      String city, Double niceScore, ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public Integer getId() { return id; }

    public String getLastName() { return lastName; }

    public String getFirstName() { return firstName; }

    public Integer getAge() { return age; }

    public String getCity() { return city; }

    public Double getNiceScore() { return niceScore; }

    public ArrayList<String> getGiftsPreferences() { return giftsPreferences; }
}
