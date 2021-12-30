package fileio;

import java.util.ArrayList;

public final class ChildUpdateInput {
    private final Integer id;
    private final Double newNiceScore;
    private final ArrayList<String> newGiftPreferences;

    public ChildUpdateInput(final Integer id, final Double newNiceScore,
                            final ArrayList<String> newGiftPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftPreferences = newGiftPreferences;
    }

    public Integer getId() {
        return id;
    }

    public Double getNewNiceScore() {
        return newNiceScore;
    }

    public ArrayList<String> getNewGiftPreferences() {
        return newGiftPreferences;
    }
}
