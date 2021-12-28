package main;

import checker.Checker;
import common.Constants;
import database.Database;
import database.Parser;
import fileio.Input;
import fileio.InputLoader;

import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        File outputDirectory = new File(Constants.OUTPUT_PATH);

        for (File file: Objects.requireNonNull(directory.listFiles())) {
            String filepath = Constants.OUTPUT_PATH + file.getName();
            File out = new File(filepath);
            //boolean isCreated = out.createNewFile();
            //if(isCreated) {
            action(file.getAbsolutePath(), filepath);
            //}
        }

        Checker.calculateScore();
    }

    /*
     * filePath1 -> input file
     * filePath2 -> output file
     */

    public static void action(final String filePath1, final String filePath2) throws IOException {
        Database database = Database.getDatabase();
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        //TODO PROGRAM
        Parser parser = new Parser();
        parser.buildDatabase(input);


        fileWriter.closeJson(arrayResult);
    }
}
