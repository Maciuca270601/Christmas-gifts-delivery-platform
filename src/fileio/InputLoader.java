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

    /**
     * The method collects data from tests and returns an Input object.
     * @return Input object
     *      -> returns an object that contains all the data collected from jsons.
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int numberOfYears = 0;
        double santaBudget = 0d;
        ArrayList<ChildInput> initialData = new ArrayList<>();
        ArrayList<GiftInput> santaGiftList = new ArrayList<>();
        ArrayList<YearDataInput> annualChanges = new ArrayList<>();

        try {
            // Parsing the contents of the JSON file using a JSONObject
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));

            // Read an "int" from the jsonObject representing numberOfYears.
            numberOfYears = ((Long) jsonObject.get("numberOfYears")).intValue();

            // Read a "double" from the jsonObject representing santaBudget.
            santaBudget = ((Long) jsonObject.get("santaBudget")).doubleValue();

            // Read an "object" from the jsonObject representing initialData.
            JSONObject initData = (JSONObject) jsonObject.get("initialData");

            // Read the list of children from the initialData object.
            JSONArray jsonChildren = (JSONArray) initData.get("children");

            // Read the list of gifts from the initialData object.
            JSONArray jsonGifts = (JSONArray) initData.get("santaGiftsList");

            // Read the list of changes from the initialData object.
            JSONArray jsonChanges = (JSONArray) jsonObject.get("annualChanges");

            if (jsonChildren != null) {
                ArrayList<ChildInput> children;
                children = Utils.convertJSONArrayChild(jsonChildren);
                initialData.addAll(children);
            } else {
                System.out.println("LIST OF CHILDREN DOES NOT EXIST!");
            }

            if (jsonGifts != null) {
                ArrayList<GiftInput> gifts;
                gifts = Utils.convertJSONArrayGift(jsonGifts);
                santaGiftList.addAll(gifts);
            } else {
                System.out.println("LIST OF GIFTS DOES NOT EXIST!");
            }

            if (jsonChanges != null) {
                for (Object jsonChange: jsonChanges) {
                    Double budget = ((Long) ((JSONObject) jsonChange)
                            .get("newSantaBudget")).doubleValue();

                    JSONArray arrayGifts = (JSONArray) ((JSONObject) jsonChange)
                            .get("newGifts");
                    ArrayList<GiftInput> gifts;
                    gifts = Utils.convertJSONArrayGift(arrayGifts);

                    JSONArray arrayChildren = (JSONArray) ((JSONObject) jsonChange)
                            .get("newChildren");
                    ArrayList<ChildInput> children;
                    children = Utils.convertJSONArrayChild(arrayChildren);

                    JSONArray arrayChanges = (JSONArray) ((JSONObject) jsonChange)
                            .get("childrenUpdates");
                    ArrayList<ChildUpdateInput> updates;
                    updates = Utils.convertJSONArrayUpdate(arrayChanges);

                    String strategy = (String) ((JSONObject) jsonChange).get("strategy");

                    YearDataInput yearData = new YearDataInput(budget, gifts, children,
                                            updates, strategy);
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
