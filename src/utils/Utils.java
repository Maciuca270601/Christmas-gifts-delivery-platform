package utils;

import common.Constants;
import database.Database;
import entities.Child;
import entities.Gift;
import fileio.ChildInput;
import fileio.ChildUpdateInput;
import fileio.GiftInput;
import fileio.YearDataInput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public final class Utils {

    private Utils() {

    }

    /**
     * This method transforms an array of JSON's into an array of strings.
     */
    public static ArrayList<String> convertJSONArrayString(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object: array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * This method transforms an array of JSON's into an array of ChildInput.
     */
    public static ArrayList<ChildInput> convertJSONArrayChild(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildInput> finalArray = new ArrayList<>();
            for (Object object: array) {
                ChildInput childInput = new ChildInput(
                        ((Long) ((JSONObject) object).get("id")).intValue(),
                        (String) ((JSONObject) object).get("lastName"),
                        (String) ((JSONObject) object).get("firstName"),
                        ((Long) ((JSONObject) object).get("age")).intValue(),
                        (String) ((JSONObject) object).get("city"),
                        ((Long) ((JSONObject) object).get("niceScore")).doubleValue(),
                        Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                .get("giftsPreferences")),
                        ((Long) ((JSONObject) object).get("niceScoreBonus")).intValue(),
                        (String) ((JSONObject) object).get("elf")
                );
                finalArray.add(childInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * This method transforms an array of JSON's into an array of GiftInput.
     */
    public static ArrayList<GiftInput> convertJSONArrayGift(final JSONArray array) {
        if (array != null) {
            ArrayList<GiftInput> finalArray = new ArrayList<>();
            for (Object object: array) {
                GiftInput giftInput = new GiftInput(
                        (String) ((JSONObject) object).get("productName"),
                        ((Long) ((JSONObject) object).get("price")).doubleValue(),
                        (String) ((JSONObject) object).get("category"),
                        ((Long) ((JSONObject) object).get("quantity")).intValue()
                );
                finalArray.add(giftInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * This method transforms an array of JSON's into an array of ChildUpdateInput.
     */
    public static ArrayList<ChildUpdateInput> convertJSONArrayUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildUpdateInput> finalArray = new ArrayList<>();
            for (Object object: array) {
                int id = ((Long) ((JSONObject) object).get("id")).intValue();
                Double niceScore = null;
                if ((((JSONObject) object).get("niceScore")) != null) {
                    niceScore = ((Long) ((JSONObject) object).get("niceScore")).doubleValue();
                }
                ArrayList<String> giftPref = Utils
                        .convertJSONArrayString((JSONArray) ((JSONObject) object)
                                .get("giftsPreferences"));
                String elf = (String) ((JSONObject) object).get("elf");
                ChildUpdateInput childUpdateInput = new ChildUpdateInput(id, niceScore,
                                                    giftPref, elf);
                finalArray.add(childUpdateInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     *  This method sorts any ArrayList<Gift> in ascending order based on the price.
     */
    public static void sortGiftList(final ArrayList<Gift> gifts) {
        gifts.sort(Comparator.comparing(Gift::getPrice));
    }

    /**
     * This method returns the specific Gift array using the String parameter to identify type.
     */
    public static ArrayList<Gift> pickArray(final String giftType) {
        return switch (giftType) {
            case "Board Games" -> Database.getDatabase().getBoardGames();
            case "Books" -> Database.getDatabase().getBooks();
            case "Clothes" -> Database.getDatabase().getClothes();
            case "Sweets" -> Database.getDatabase().getSweets();
            case "Technology" -> Database.getDatabase().getTechnology();
            case "Toys" -> Database.getDatabase().getToys();
            default -> null;
        };
    }

    /**
     * This method adds new children in the list of children.
     */
    public static void addNewChildren(final YearDataInput currChanges) {
        // Check if there are any changes to be done
        if (currChanges.getNewChildren() != null || currChanges.getNewChildren().size() != 0) {

            for (ChildInput childInput: currChanges.getNewChildren()) {
                Child child = new Child(childInput);
                // If child is not yet young adult we should add him/her to the list
                if (child.getAge() <= Constants.MAX_AGE) {
                    Database.getDatabase().getListOfChildren().add(child);
                }
            }
        }
    }

    /**
     * This method checks if a given id is valid.
     */
    public static boolean checkId(final Integer id) {
        // Id has not been found
        boolean valid = false;
        for (int i = 0; i < Database.getDatabase().getListOfChildren().size() && !valid; i++) {
            Child child = Database.getDatabase().getListOfChildren().get(i);
            if (Objects.equals(child.getId(), id)) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * This method returns the index of the child with the specific id.
     */
    public static int getIndexChild(final Integer id) {
        for (int i = 0; i < Database.getDatabase().getListOfChildren().size(); i++) {
            Child child = Database.getDatabase().getListOfChildren().get(i);
            if (Objects.equals(child.getId(), id)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * This method updates the info about the kinds from the already existing list.
     */
    public static void updateExistingChildren(final YearDataInput currChanges) {
        // Check if there are any changes to be done
        if (currChanges.getChildrenUpdates() != null
                || currChanges.getChildrenUpdates().size() != 0) {

            for (ChildUpdateInput childUpdateInput: currChanges.getChildrenUpdates()) {
                Integer id = childUpdateInput.getId();

                // Check if child with specific id exist
                if (checkId(id)) {
                    int index = getIndexChild(id);
                    // Update niceScore if necessary
                    if (childUpdateInput.getNewNiceScore() != null) {
                        Double score = childUpdateInput.getNewNiceScore();
                        Database.getDatabase().getListOfChildren().get(index).getNiceScoreHistory()
                                .add(score);
                    }

                    // Update gift preferences if necessary
                    if (childUpdateInput.getNewGiftPreferences() != null || childUpdateInput
                            .getNewGiftPreferences().size() != 0) {
                        ArrayList<String> strings = childUpdateInput.getNewGiftPreferences();
                        for (int i = strings.size() - 1; i >= 0; i--) {
                            // ok == 0 -> String has not been found in the list.
                            int ok = 0;
                            int removeIndex = 0;
                            Child child = Database.getDatabase().getListOfChildren().get(index);
                            for (int j = 0; j < child.getGiftsPreferences().size()
                                    && ok == 0; j++) {
                                if (strings.get(i).equals(child.getGiftsPreferences().get(j))) {
                                    ok = 1;
                                    removeIndex = j;
                                }
                            }
                            if (ok == 1) {
                                child.getGiftsPreferences().remove(removeIndex);
                            }
                            child.getGiftsPreferences().add(0, strings.get(i));
                        }
                    }

                    // Update elf is necessary
                    if (childUpdateInput.getElf() != null) {
                        String newElf = childUpdateInput.getElf();
                        Database.getDatabase().getListOfChildren().get(index).setElf(newElf);
                    }
                }
            }
        }
    }

    /**
     * This method adds new gifts in the list of gifts.
     */
    public static void addNewGifts(final YearDataInput currChanges) {
        // Check if there are any changes to be done
        if (currChanges.getNewGifts() != null || currChanges.getNewGifts().size() != 0) {
            for (GiftInput giftInput: currChanges.getNewGifts()) {
                Gift gift = new Gift(giftInput);
                Database.getDatabase().getListOfGifts().add(gift);
                String giftType = gift.getCategory();
                ArrayList<Gift> gifts = Utils.pickArray(giftType);
                gifts.add(gift);
            }
        }
    }

    /**
     * This method is used to remove duplicates from gifts preferences if there are any.
     */
    public static ArrayList<String> removeDuplicatesGiftPref(final Child child) {
        ArrayList<String> oldPref = child.getGiftsPreferences();
        ArrayList<String> newPref = new ArrayList<>();

        for (String s: oldPref) {
            if (!newPref.contains(s)) {
                newPref.add(s);
            }
        }
        return newPref;
    }
}
