package fileio;

import java.util.ArrayList;

public class ChildUpdateInput {
    private Integer id;
    private Double newNiceScore;
    private ArrayList<String> newGiftPreferences;

    public ChildUpdateInput(Integer id, Double newNiceScore, ArrayList<String> newGiftPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftPreferences = newGiftPreferences;
    }

    public Integer getId() { return id; }

    public Double getNewNiceScore() { return newNiceScore; }

    public ArrayList<String> getNewGiftPreferences() { return newGiftPreferences; }
}
