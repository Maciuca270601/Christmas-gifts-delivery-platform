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
                        (Integer) ((JSONObject) object).get("id"),
                        (String) ((JSONObject) object).get("lastName"),
                        (String) ((JSONObject) object).get("firstName"),
                        (Integer) ((JSONObject) object).get("age"),
                        (String) ((JSONObject) object).get("city"),
                        (Double) ((JSONObject) object).get("niceScore"),
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
                        (Double) ((JSONObject) object).get("price"),
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
                ChildUpdateInput childUpdateInput = new ChildUpdateInput(
                        (Integer) ((JSONObject) object).get("id"),
                        (Double) ((JSONObject) object).get("niceScore"),
                        Utils.convertJSONArrayString((JSONArray) ((JSONObject)object).get("giftsPreferences"))
                );
                finalArray.add(childUpdateInput);
            }
            return finalArray;
        } else {
            return null;
        }
    }


}
