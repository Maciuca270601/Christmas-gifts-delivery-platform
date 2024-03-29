package fileio;

import java.util.ArrayList;

public final class ChildInput {

    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final Integer age;
    private final String city;
    private final Double niceScore;
    private final ArrayList<String> giftsPreferences;
    private final Integer niceScoreBonus;
    private final String elf;

    public ChildInput(final Integer id, final String lastName, final String firstName,
                      final Integer age, final String city, final Double niceScore,
                      final ArrayList<String> giftsPreferences, final Integer niceScoreBonus,
                      final String elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
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

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Integer getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public String getElf() {
        return elf;
    }
}
