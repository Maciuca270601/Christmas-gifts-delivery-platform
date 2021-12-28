package utils;

import fileio.ChildInput;
import fileio.ChildUpdateInput;
import fileio.GiftInput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

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


}
