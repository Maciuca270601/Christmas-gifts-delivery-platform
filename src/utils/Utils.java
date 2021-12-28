package utils;

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

public class Utils {

    private Utils() {
    }

    /*
     * Transforms an array of JSON's into an array of strings
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

    /*
     * Transforms an array of JSON's into an array of ChildInput
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
                        Utils.convertJSONArrayString((JSONArray) ((JSONObject) object).get("giftsPreferences"))
                );
                finalArray.add(childInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /*
     * Transforms an array of JSON's into an array of GiftInput
     */
    public static ArrayList<GiftInput> convertJSONArrayGift(final JSONArray array) {
        if (array != null) {
            ArrayList<GiftInput> finalArray = new ArrayList<>();
            for (Object object: array) {
                GiftInput giftInput = new GiftInput(
                        (String) ((JSONObject) object).get("productName"),
                        ((Long) ((JSONObject) object).get("price")).doubleValue(),
                        (String) ((JSONObject) object).get("category")
                );
                finalArray.add(giftInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /*
     * Transforms an array of JSON's into an array of ChildUpdateInput
     */
    public static ArrayList<ChildUpdateInput> convertJSONArrayUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildUpdateInput> finalArray = new ArrayList<>();
            for (Object object: array) {
                int id = ((Long) ((JSONObject) object).get("id")).intValue();
                Double niceScore = null;
                if ( (((JSONObject) object).get("niceScore")) != null) {
                    niceScore = ((Long) ((JSONObject) object).get("niceScore")).doubleValue();
                }
                ArrayList<String> giftPref = Utils.convertJSONArrayString((JSONArray) ((JSONObject)object).get("giftsPreferences"));
                ChildUpdateInput childUpdateInput = new ChildUpdateInput(id, niceScore, giftPref);
                finalArray.add(childUpdateInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    public static ArrayList<Gift> sortGiftList(ArrayList<Gift> gifts) {
        gifts.sort(Comparator.comparing(Gift::getPrice));
        return gifts;
    }

    public static ArrayList<Gift> pickArray(String giftType) {
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

    public static void AddNewChildren(YearDataInput currChanges) {
        //check if there are any changes to be done
        if (currChanges.getNewChildren() != null || currChanges.getNewChildren().size() != 0) {

            for (ChildInput childInput: currChanges.getNewChildren()) {
                Child child = new Child(childInput);
                // if child is not yet young adult we should add him/her to the list
                if (child.getAge() <= 18) {
                    Database.getDatabase().getListOfChildren().add(child);
                }
            }
        }
    }

    public static boolean checkId(Integer id) {
        boolean sem = false; // id has not been found
        for (int i = 0; i < Database.getDatabase().getListOfChildren().size() && !sem; i++) {
            Child child = Database.getDatabase().getListOfChildren().get(i);
            if (Objects.equals(child.getId(), id)) {
                sem = true;
            }
        }
        return sem;
    }

    public static int getIndexChild(Integer id) {
        for (int i = 0; i < Database.getDatabase().getListOfChildren().size(); i++) {
            Child child = Database.getDatabase().getListOfChildren().get(i);
            if (Objects.equals(child.getId(), id)) {
                return i;
            }
        }
        return 0;
    }

    public static void UpdateExistingChildren(YearDataInput currChanges) {
        // check if there are any changes to be done
        if (currChanges.getChildrenUpdates() != null || currChanges.getChildrenUpdates().size() != 0) {

            for (ChildUpdateInput childUpdateInput: currChanges.getChildrenUpdates()) {
                Integer id = childUpdateInput.getId();

                // check if child with specific id exist
                if (checkId(id)) {
                    int index = getIndexChild(id);
                    // update niceScore if necessary
                    if (childUpdateInput.getNewNiceScore() != null) {
                        Double score = childUpdateInput.getNewNiceScore();
                        Database.getDatabase().getListOfChildren().get(index).getNiceScoreHistory().add(score);
                    }

                    // update gift preferences if necessary
                    if (childUpdateInput.getNewGiftPreferences() != null || childUpdateInput.getNewGiftPreferences().size() != 0) {
                        ArrayList<String> strings = childUpdateInput.getNewGiftPreferences();
                        for (int i = strings.size() - 1; i >= 0; i--) {
                            int ok = 0; // to remove
                            int removeIndex = 0;
                            Child child = Database.getDatabase().getListOfChildren().get(index);
                            for (int j = 0; j < child.getGiftsPreferences().size() && ok == 0; j++) {
                                if (strings.get(i).equals(child.getGiftsPreferences().get(j))) {
                                    ok = 1;
                                    removeIndex = j;
                                }
                            }
                            if (ok == 1) {
                                child.getGiftsPreferences().remove(removeIndex);
                            }
                            child.getGiftsPreferences().add(0,strings.get(i));
                        }
                    }
                }
            }
        }
    }

    public static void AddNewGifts(YearDataInput currChanges) {
        //check if there are any changes to be done
        if (currChanges.getNewGifts() != null || currChanges.getNewGifts().size() != 0) {
            for (GiftInput giftInput: currChanges.getNewGifts()) {
                Gift gift = new Gift(giftInput);
                Database.getDatabase().getListOfGifts().add(gift);
            }
        }
    }



}
