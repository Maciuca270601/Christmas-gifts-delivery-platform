package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * The class reads and parses data from the tests
 */
public class InputLoader {

    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /*
     * The method reads data from tests and returns an Input object.
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        Integer numberOfYears = 0;
        Double santaBudget = 0d;
        ArrayList<ChildInput> initialData = new ArrayList<>();
        ArrayList<GiftInput> santaGiftList = new ArrayList<>();
        ArrayList<YearDataInput> annualChanges = new ArrayList<>();

        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
            JSONObject database = (JSONObject) jsonObject.get("database");
            JSONObject jsonNumberOfYears = (JSONObject) database.get("numberOfYears");
            JSONObject jsonSantaBudget = (JSONObject) database.get("santaBudget");
            JSONArray jsonChildren = (JSONArray) database.get("children");
            JSONArray jsonGifts = (JSONArray) database.get("santaGiftList");
            JSONArray jsonChanges = (JSONArray) database.get("annualChanges");

            numberOfYears = (Integer) jsonNumberOfYears.get("numberOfYears");

            santaBudget = (Double) jsonSantaBudget.get("santaBudget");

            if (jsonChildren != null) {
                ArrayList<ChildInput> children = new ArrayList<>();
                children = Utils.convertJSONArrayChild(jsonChildren);
                initialData.addAll(children);
            } else {
                System.out.println("LIST OF CHILDREN DOES NOT EXIST!");
            }

            if (jsonGifts != null) {
                ArrayList<GiftInput> gifts = new ArrayList<>();
                gifts = Utils.convertJSONArrayGift(jsonGifts);
                santaGiftList.addAll(gifts);
            } else {
                System.out.println("LIST OF GIFTS DOES NOT EXIST!");
            }

            if (jsonChanges != null) {
                for (Object jsonChange: jsonChanges) {
                    Double budget = (Double) ((JSONObject) jsonChange).get("newSantaBudget");

                    JSONArray arrayGifts = (JSONArray) ((JSONObject) jsonChange).get("newGifts");
                    ArrayList<GiftInput> gifts = new ArrayList<>();
                    gifts = Utils.convertJSONArrayGift(arrayGifts);

                    JSONArray arrayChildren = (JSONArray) ((JSONObject) jsonChange).get("newChildren");
                    ArrayList<ChildInput> children = new ArrayList<>();
                    children = Utils.convertJSONArrayChild(arrayChildren);

                    JSONArray arrayChanges = (JSONArray) ((JSONObject) jsonChange).get("childrenUpdates");
                    ArrayList<ChildUpdateInput> updates = new ArrayList<>();
                    updates = Utils.convertJSONArrayUpdate(arrayChanges);

                    YearDataInput yearData = new YearDataInput(budget, gifts, children, updates);
                    annualChanges.add(yearData);
                }
            } else {
                System.out.println("LIST OF CHANGES DOES NOT EXIST");
            }

            if (jsonChildren == null) {
                initialData = null;
            }

            if (jsonGifts == null) {
                santaGiftList = null;
            }

            if (jsonChanges == null) {
                annualChanges = null;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return new Input(numberOfYears, santaBudget, initialData, santaGiftList, annualChanges);
    }

}
