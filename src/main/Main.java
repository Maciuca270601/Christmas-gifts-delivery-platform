package main;

import checker.Checker;
import fileio.Input;
import fileio.InputLoader;

import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        Checker.calculateScore();
    }

    /*
     * filePath1 -> input file
     * filePath2 -> output file
     */

    public static void action(final String filePath1, final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        //TODO PROGRAM

        fileWriter.closeJson(arrayResult);
    }
}
